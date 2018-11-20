package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.Config.SecurityConfig;
import ar.com.healthyapple.crm_web.controller.DtoConverter.ProductDtoConverter;
import ar.com.healthyapple.crm_web.controller.DtoConverter.ProductProfileDtoConverter;
import ar.com.healthyapple.crm_web.dto.Product.*;
import ar.com.healthyapple.crm_web.model.Product.*;
import ar.com.healthyapple.crm_web.service.Client.ClientService;
import ar.com.healthyapple.crm_web.service.Product.ProductProfileService;
import ar.com.healthyapple.crm_web.service.Product.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest({ProductController.class})
@ContextConfiguration(classes={ProductController.class, SecurityConfig.class})
class ProductControllerTest {

    private final static Long ID = 1L;

    private final static Long CLIENT_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductDtoConverter productDtoConverter;

    @MockBean
    private ProductProfileDtoConverter productProfileDtoConverter;

    @MockBean
    private  ProductService productService;

    @MockBean
    private ProductProfileService productProfileService;

    @MockBean
    private ClientService clientService;

    private Product request;

    private Product response;

    private ProductDto requestDto;

    private ProductDto responseDto;


    @BeforeEach
    void setUp() {

        ComponentType componentType = new ComponentType("aaa");
        List<Specification> specifications = new ArrayList<>(Arrays.asList(new Specification()));
        Component component  = new Component("description", componentType, specifications);

        ProductType productType = new ProductType("productType");
        List<Component> components = new ArrayList<>(Arrays.asList(component));
        request = new Product(productType, "description", components);
        response = new Product(productType, "description", components);
        response.setId(ID);

        ComponentTypeDto componentTypeDto = new ComponentTypeDto("aaa");
        List<SpecificationDto> specificationsDto = new ArrayList<>(Arrays.asList(new SpecificationDto()));
        ComponentDto componentDto = new ComponentDto("description", componentTypeDto, specificationsDto);

        ProductTypeDto productTypeDto = new ProductTypeDto("productTypeDto");
        List<ComponentDto> componentsDto = new ArrayList<>(Arrays.asList(componentDto));

        ComponentProfileDto componentProfileDto = new ComponentProfileDto("type", "description", Boolean.FALSE, new HashMap<>());
        ProductProfileDto productProfileDto = new ProductProfileDto("type", "description", Arrays.asList(componentProfileDto));

        requestDto = new ProductDto("descriptionDto", componentsDto, productTypeDto, productProfileDto);
        responseDto = new ProductDto( "descriptionDto", componentsDto, productTypeDto,productProfileDto);
        responseDto.setId(ID);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createProduct() throws Exception {
        when(productDtoConverter.convertToEntity(requestDto))
                .thenReturn(request);
        when(productService.create(request)).thenReturn(response);
        when(productDtoConverter.convertToDto(response))
                .thenReturn(responseDto);
        MvcResult result = this.mockMvc.perform(post(Uris.PRODUCTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        assertThat(objectMapper.readValue(body, ProductDto.class)).isEqualTo(responseDto);
    }

    @Test
    void readProduct() throws Exception {
        when(productService.read(ID)).thenReturn(response);
        when(productDtoConverter.convertToDto(response))
                .thenReturn(responseDto);
        MvcResult result = this.mockMvc.perform(get(Uris.PRODUCTS + Uris.ID, ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        assertThat(objectMapper.readValue(body, ProductDto.class)).isEqualTo(responseDto);
    }

    @Test
    void updateProduct() throws Exception {
        when(productDtoConverter.convertToEntity(requestDto))
                .thenReturn(request);
        when(productService.update(request)).thenReturn(response);
        when(productDtoConverter.convertToDto(response))
                .thenReturn(responseDto);
        MvcResult result = this.mockMvc.perform(put(Uris.PRODUCTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        assertThat(objectMapper.readValue(body, ProductDto.class)).isEqualTo(responseDto);
    }

    @Test
    void deleteProductById() throws Exception {
        doNothing().when(clientService).deleteClientProductById(CLIENT_ID, ID);
        this.mockMvc.perform(delete(Uris.PRODUCTS + Uris.ID, ID)
                .param("client_id", String.valueOf(CLIENT_ID))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Disabled("Not Implemented Yet")
    void findAllProducts() {
    }

    @Test
    @DisplayName("Client Products Id And Descriptions")
    void findProductsIdAndDescriptionByClientId() throws Exception {
        Map<Long, String> productIdAndDescription = new HashMap<>();
        productIdAndDescription.put(1L, "Computadora Imac");
        productIdAndDescription.put(2L, "Computadora MacBook");
        when(clientService.findProductsIdAndDescriptionByClientId(CLIENT_ID)).thenReturn(productIdAndDescription);

        MvcResult result = this.mockMvc.perform(get(Uris.PRODUCTS + Uris.NAMES + Uris.CLIENTS + Uris.ID, CLIENT_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        Map<Long, String> resultMap = objectMapper.readValue(body, new TypeReference<HashMap<Long, String>>() {});
        assertThat(resultMap).isEqualTo(productIdAndDescription);
    }

    @Test
    void findProductsByClientId() throws Exception {
        ComponentType componentType = new ComponentType("aaa");
        List<Specification> specifications = new ArrayList<>(Arrays.asList(new Specification()));
        Component component  = new Component("description", componentType, specifications);

        ProductType productType = new ProductType("productType");
        List<Component> components = new ArrayList<>(Arrays.asList(component));
        Product clientProduct = new Product(productType, "description", components);

        List<Product> products = new ArrayList<>(Arrays.asList(clientProduct));

        when(clientService.findProductsByClientId(CLIENT_ID)).thenReturn(products);


        MvcResult result = this.mockMvc.perform(get(Uris.PRODUCTS + Uris.CLIENTS + Uris.ID, CLIENT_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        List<Product> resultList = objectMapper.readValue(body, new TypeReference<List<Product>>(){});
        assertThat(resultList).isEqualTo(products);

    }
}