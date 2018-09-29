package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.Config.SecurityConfig;
import ar.com.healthyapple.crm_web.dto.Computer.ClientDto;
import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Product;
import ar.com.healthyapple.crm_web.model.Sale.Sale;
import ar.com.healthyapple.crm_web.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({ClientController.class})
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

    private Client clientRequest;

    private Client clientResponse;

    private ClientDto clientRequestDto;

    private ClientDto clientResponseDto;

    @Before
    public void setUp() throws Exception {
        clientRequest = new Client(1111123123213L, LocalDate.of(2018, 2, 1), "Juan", "Perez", "jp@gmail.com", "Lalala");
        clientResponse = new Client(1111123123213L, LocalDate.of(2018, 2, 1), "Juan", "Perez", "jp@gmail.com", "Lalala");
        clientRequestDto = new ClientDto(1111123123213L, "01-02-2018", "Juan", "Perez", "jp@gmail.com", string);
        clientResponseDto = new ClientDto(1111123123213L, "01-02-2018","Juan", "Perez", "jp@gmail.com", string);
    }

    @Test
    public void createShouldReturnClientDtoAndStatusCreated() throws Exception {
        when(entityDtoConverter.convertToEntity(clientRequestDto, Client.class))
                .thenReturn(clientRequest);
        when(clientService.create(clientRequest)).thenReturn(clientResponse);
        when(entityDtoConverter.convertToDto(clientResponse, ClientDto.class))
                .thenReturn(clientResponseDto);
        MvcResult result = this.mockMvc.perform(post(Uris.CLIENTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientRequestDto)))
                .andExpect(status().isCreated())
                .andReturn();
        java.lang.String body = result.getResponse().getContentAsString();
        assertThat(objectMapper.readValue(body, ClientDto.class)).isEqualTo(clientResponseDto);
    }

    @Test
    public void createDuplicateShouldReturnAlreadyExists() throws Exception {
        when(entityDtoConverter.convertToEntity(clientRequestDto, Client.class))
                .thenReturn(clientRequest);
        when(clientService.create(clientRequest)).thenThrow(AlreadyExistException.class);
        this.mockMvc.perform(post(Uris.CLIENTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void readShouldReturnClientDtoAndStatusOk() throws Exception {
        when(entityDtoConverter.convertToEntity(clientRequestDto, Client.class))
                .thenReturn(clientRequest);
        when(clientService.read(clientResponse.getMobile())).thenReturn(clientResponse);
        when(entityDtoConverter.convertToDto(clientResponse, ClientDto.class))
               .thenReturn(clientResponseDto);
        MvcResult result = this.mockMvc.perform(get(Uris.CLIENTS + Uris.ID, clientResponse.getMobile())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        java.lang.String body = result.getResponse().getContentAsString();
        assertThat(objectMapper.readValue(body, ClientDto.class)).isEqualTo(clientResponseDto);
    }

    @Test
    public void readNonExistentResourceShouldReturnNotFound() throws Exception {
        when(entityDtoConverter.convertToEntity(clientRequestDto, Client.class))
                .thenReturn(clientRequest);
        when(clientService.read(clientResponse.getMobile())).thenThrow(NotFoundException.class);
        this.mockMvc.perform(get(Uris.CLIENTS + Uris.ID, clientResponse.getMobile())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateShouldReturnClientDtoAndStautsOk() throws Exception {
        when(entityDtoConverter.convertToEntity(clientRequestDto, Client.class))
                .thenReturn(clientRequest);
        when(clientService.update(clientRequest)).thenReturn(clientResponse);
        when(entityDtoConverter.convertToDto(clientResponse, ClientDto.class))
                .thenReturn(clientResponseDto);
        MvcResult result = this.mockMvc.perform(put(Uris.CLIENTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientRequestDto)))
                .andExpect(status().isOk())
                .andReturn();
        java.lang.String body = result.getResponse().getContentAsString();
        assertThat(objectMapper.readValue(body, ClientDto.class)).isEqualTo(clientResponseDto);
    }

    @Test
    public void updateNonExistentResourceShouldReturnNotFound() throws Exception {
        when(entityDtoConverter.convertToEntity(clientRequestDto, Client.class))
                .thenReturn(clientRequest);
        when(clientService.update(clientRequest)).thenThrow(NotFoundException.class);
        this.mockMvc.perform(put(Uris.CLIENTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientRequestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteByIdShouldReturnOk() throws Exception {
        doNothing().when(clientService).deleteByMobile(clientResponse.getMobile());
        this.mockMvc.perform(delete(Uris.CLIENTS + Uris.ID, clientResponse.getMobile()))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteByNonExistentIdShouldReturnNotFound() throws Exception {
        doThrow(NotFoundException.class).when(clientService).deleteByMobile(ID);
        this.mockMvc.perform(delete(Uris.CLIENTS + Uris.ID, ID))
                .andExpect(status().isNotFound());
    }

}