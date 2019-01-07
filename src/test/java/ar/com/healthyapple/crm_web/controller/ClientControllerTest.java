package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.Config.SecurityConfig;
import ar.com.healthyapple.crm_web.Utils.ClientFactory;
import ar.com.healthyapple.crm_web.Utils.ProductDtoFactory;
import ar.com.healthyapple.crm_web.Utils.ProductFactory;
import ar.com.healthyapple.crm_web.Utils.ThinClientDtoFactory;
import ar.com.healthyapple.crm_web.controller.DtoConverter.ClientDtoConverter;
import ar.com.healthyapple.crm_web.controller.DtoConverter.ProductDtoConverter;
import ar.com.healthyapple.crm_web.controller.DtoConverter.ThinClientDtoConverter;
import ar.com.healthyapple.crm_web.dto.Client.ThinClientDto;
import ar.com.healthyapple.crm_web.dto.Product.ProductDto;
import ar.com.healthyapple.crm_web.dto.Sale.SaleDto;
import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Product.Product;
import ar.com.healthyapple.crm_web.model.Sale.Sale;
import ar.com.healthyapple.crm_web.service.Client.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
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
    private ClientDtoConverter clientDtoConverter;

    @MockBean
    private ThinClientDtoConverter thinClientDtoConverter;

    @MockBean
    private ProductDtoConverter productDtoConverter;

    @MockBean
    private ClientService clientService;

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


        products = Arrays.asList(ProductFactory.makeProduct());
        productDtoList = Arrays.asList(ProductDtoFactory.makeProductDto());
        clientRequest = ClientFactory.makeClient();
        clientResponse = ClientFactory.makeClient();
        clientRequestDto = ThinClientDtoFactory.makeThinClientDto();
        clientResponseDto = ThinClientDtoFactory.makeThinClientDto();
        clientResponse.setId(ID);
    }

    @Test
    public void createShouldReturnClientDtoAndStatusCreated() throws Exception {
        when(thinClientDtoConverter.convertToEntity(clientRequestDto))
                .thenReturn(clientRequest);
        when(clientService.create(clientRequest)).thenReturn(clientResponse);
        when(thinClientDtoConverter.convertToDto(clientResponse))
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
        when(thinClientDtoConverter.convertToEntity(clientRequestDto))
                .thenReturn(clientRequest);
        when(clientService.create(clientRequest)).thenThrow(AlreadyExistException.class);
        this.mockMvc.perform(post(Uris.CLIENTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void readShouldReturnClientDtoAndStatusOk() throws Exception {
        when(thinClientDtoConverter.convertToEntity(clientRequestDto))
                .thenReturn(clientRequest);
        when(clientService.read(clientResponse.getId())).thenReturn(clientResponse);
        when(thinClientDtoConverter.convertToDto(clientResponse))
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
        when(thinClientDtoConverter.convertToEntity(clientRequestDto))
                .thenReturn(clientRequest);
        when(clientService.read(clientResponse.getId())).thenThrow(NotFoundException.class);
        this.mockMvc.perform(get(Uris.CLIENTS + Uris.ID, clientResponse.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateShouldReturnThinClientDtoAndStautsOk() throws Exception {
        when(thinClientDtoConverter.convertToEntity(clientRequestDto))
                .thenReturn(clientRequest);
        when(clientService.update(clientRequest)).thenReturn(clientResponse);
        when(thinClientDtoConverter.convertToDto(clientResponse))
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
        when(thinClientDtoConverter.convertToEntity(clientRequestDto))
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

}