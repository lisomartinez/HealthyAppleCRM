package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.Config.SecurityConfig;
import ar.com.healthyapple.crm_web.dto.Client.ThinClientDto;
import ar.com.healthyapple.crm_web.dto.Product.ProductDto;
import ar.com.healthyapple.crm_web.dto.SaleDto;
import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Product.Product;
import ar.com.healthyapple.crm_web.model.Sale.Sale;
import ar.com.healthyapple.crm_web.service.Client.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest({ClientController.class})
//@EnableSpringDataWebSupport
@ContextConfiguration(classes={ClientController.class, SecurityConfig.class})
public class ClientControllerTest {

    private final static Long ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EntityDtoConverter entityDtoConverter;

    @MockBean
    private ClientService clientService;


    private String string;

    @MockBean
    private List<Product> products;

    @MockBean
    private List<Sale> services;

    @MockBean
    private List<ProductDto> productDtoList;

    @MockBean
    private List<SaleDto> saleDtoList;


    private Product productRequest;


    private ProductDto productDtoResponse;

    private Client clientRequest;

    private Client clientResponse;

    private ThinClientDto clientRequestDto;

    private ThinClientDto clientResponseDto;

    @BeforeEach
    public void setUp() {

        products = Arrays.asList(productRequest);
        productDtoList = Arrays.asList(productDtoResponse);
        clientRequest = new Client(1111123123213L, LocalDate.of(2018, 2, 1), "Juan", "Perez", "jp@gmail.com", "Lalala", products, services);
        clientResponse = new Client(1111123123213L, LocalDate.of(2018, 2, 1), "Juan", "Perez", "jp@gmail.com", "Lalala", products, services);
        clientRequestDto = new ThinClientDto(1111123123213L, "Juan", "Perez", "jp@gmail.com", "Lalala", LocalDate.of(2018, 2, 1));
        clientResponseDto = new ThinClientDto(1111123123213L, "Juan", "Perez", "jp@gmail.com", "Lalala", LocalDate.of(2018, 2, 1));
        clientResponse.setId(ID);
    }

    @Test
    public void createShouldReturnClientDtoAndStatusCreated() throws Exception {
        when(entityDtoConverter.convertThinClientToEntity(clientRequestDto, Client.class))
                .thenReturn(clientRequest);
        when(clientService.create(clientRequest)).thenReturn(clientResponse);
        when(entityDtoConverter.convertToThinClientDto(clientResponse, ThinClientDto.class))
                .thenReturn(clientResponseDto);
        MvcResult result = this.mockMvc.perform(post(Uris.CLIENTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientRequestDto)))
                .andExpect(status().isCreated())
                .andReturn();
        java.lang.String body = result.getResponse().getContentAsString();
        assertThat(objectMapper.readValue(body, ThinClientDto.class)).isEqualTo(clientResponseDto);
    }

    @Test
    public void createDuplicateShouldReturnAlreadyExists() throws Exception {
        when(entityDtoConverter.convertThinClientToEntity(clientRequestDto, Client.class))
                .thenReturn(clientRequest);
        when(clientService.create(clientRequest)).thenThrow(AlreadyExistException.class);
        this.mockMvc.perform(post(Uris.CLIENTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void readShouldReturnClientDtoAndStatusOk() throws Exception {
        when(entityDtoConverter.convertThinClientToEntity(clientRequestDto, Client.class))
                .thenReturn(clientRequest);
        when(clientService.read(clientResponse.getId())).thenReturn(clientResponse);
        when(entityDtoConverter.convertToThinClientDto(clientResponse, ThinClientDto.class))
               .thenReturn(clientResponseDto);
        MvcResult result = this.mockMvc.perform(get(Uris.CLIENTS + Uris.ID, clientResponse.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        java.lang.String body = result.getResponse().getContentAsString();
        assertThat(objectMapper.readValue(body, ThinClientDto.class)).isEqualTo(clientResponseDto);
    }

    @Test
    public void readNonExistentResourceShouldReturnNotFound() throws Exception {
        when(entityDtoConverter.convertThinClientToEntity(clientRequestDto, Client.class))
                .thenReturn(clientRequest);
        when(clientService.read(clientResponse.getId())).thenThrow(NotFoundException.class);
        this.mockMvc.perform(get(Uris.CLIENTS + Uris.ID, clientResponse.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateShouldReturnThinClientDtoAndStautsOk() throws Exception {
        when(entityDtoConverter.convertThinClientToEntity(clientRequestDto, Client.class))
                .thenReturn(clientRequest);
        when(clientService.update(clientRequest)).thenReturn(clientResponse);
        when(entityDtoConverter.convertToThinClientDto(clientResponse, ThinClientDto.class))
                .thenReturn(clientResponseDto);
        MvcResult result = this.mockMvc.perform(put(Uris.CLIENTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientRequestDto)))
                .andExpect(status().isOk())
                .andReturn();
        java.lang.String body = result.getResponse().getContentAsString();
        assertThat(objectMapper.readValue(body, ThinClientDto.class)).isEqualTo(clientResponseDto);
    }

    @Test
    public void updateNonExistentResourceShouldReturnNotFound() throws Exception {
        when(entityDtoConverter.convertThinClientToEntity(clientRequestDto, Client.class))
                .thenReturn(clientRequest);
        when(clientService.update(clientRequest)).thenThrow(NotFoundException.class);
        this.mockMvc.perform(put(Uris.CLIENTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientRequestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteByIdShouldReturnOk() throws Exception {
        doNothing().when(clientService).deleteById(clientResponse.getId());
        this.mockMvc.perform(delete(Uris.CLIENTS + Uris.ID, clientResponse.getId()))
                .andExpect(status().isOk());
    }



//    @Test
//    public void getProdutsShouldReturnResources() throws Exception {
//        when(clientService.findProductsByMobile(clientRequest.getMobile()))
//                .thenReturn(products);
//
//         when(entityDtoConverter.convertToDto(productRequest, ProductDto.class)).thenReturn(productDtoResponse);
//
//        MvcResult result = this.mockMvc.perform(get(Uris.CLIENTS + Uris.ID + Uris.PRODUCTS, clientResponse.getMobile())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andReturn();
//        ProductDto[] prods = objectMapper.readValue(result.getResponse().getContentAsString(), ProductDto[].class);
//
//        assertThat(Arrays.asList(prods)).isEqualTo(productDtoList);
//    }
}