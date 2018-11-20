package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.Config.SecurityConfig;
import ar.com.healthyapple.crm_web.Utils.*;
import ar.com.healthyapple.crm_web.controller.DtoConverter.QuoteDtoConverter;
import ar.com.healthyapple.crm_web.controller.DtoConverter.RequestQuoteDtoConverter;
import ar.com.healthyapple.crm_web.controller.DtoConverter.ThinClientDtoConverter;
import ar.com.healthyapple.crm_web.dto.Client.ThinClientDto;
import ar.com.healthyapple.crm_web.dto.Product.StateBasedProductDto;
import ar.com.healthyapple.crm_web.dto.Sale.SaleDto;
import ar.com.healthyapple.crm_web.dto.quote.QuoteDto;
import ar.com.healthyapple.crm_web.dto.quote.QuoteItemDto;
import ar.com.healthyapple.crm_web.dto.quote.RequestQuoteDto;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Product.StateBasedProduct;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;
import ar.com.healthyapple.crm_web.service.Client.ClientService;
import ar.com.healthyapple.crm_web.service.Quote.*;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest({QuoteController.class})
@ContextConfiguration(classes={QuoteController.class, SecurityConfig.class})
public class QuoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ThinClientDtoConverter thinClientDtoConverter;

    @MockBean
    private RequestQuoteDtoConverter requestQuoteDtoConverter;

    @MockBean
    private QuoteDtoConverter quoteDtoConverter;

    @MockBean
    private ClientService clientService;

    @MockBean
    private QuoteStateBasedServiceFactory factory;

    @MockBean
    private NewQuoteService newQuoteService;

    @MockBean
    private QuotedQuoteService quotedQuoteService;

    @MockBean
    private WaitingResponseQuoteService waitingResponseQuoteService;

    @MockBean
    private RequestedQuoteService requestedQuoteService;

    @MockBean
    private SaleController saleController;


    private Client client;

    private QuoteItemDto quoteItemDto;

    private StateBasedProduct stateBasedProduct;

    private StateBasedProductDto stateBasedProductDto;

    private ThinClientDto thinClientDto;

    private Quote quoteRequest;

    private Quote quoteResponse;

    private RequestQuoteDto requestQuoteDto;

    private SaleDto saleDtoResponse;

    private QuoteDto quoteDtoRequest;

    private QuoteDto quoteDtoResponse;


    @BeforeEach
    public void setUp() throws Exception {

        List<StateBasedProduct> products = new ArrayList<>(Arrays.asList(StateBasedProductFactory.makeProduct()));
        thinClientDto = ThinClientDtoFactory.makeThinClientDto();
        client = ClientFactory.makeClient();

        saleDtoResponse = SaleDtoFactory.makeSaleDto();

        requestQuoteDto = RequestoDtoFactory.makeRequestoDto();

        quoteRequest = QuoteFactory.makeAskQuote();

        quoteResponse = QuoteFactory.makeQuote();
        quoteDtoRequest = QuoteDtoFactory.makeQuoteDto();


        List<StateBasedProductDto> productsDtos = new ArrayList<>(Arrays.asList(StateBasedProductDtoFactory.makeProductDto()));

        quoteDtoResponse = QuoteDtoFactory.makeQuoteDto();

    }

    @Test
    public void requestQuote_RequestDto_ReturnsQuoteDto() throws Exception {

        quoteRequest.setStatus(QuoteState.ASK);

        when(requestQuoteDtoConverter.convertToQuoteDto(requestQuoteDto)).thenReturn(quoteDtoRequest);
        when(clientService.read(thinClientDto.getId())).thenReturn(client);
        when(quoteDtoConverter.convertToEntity(quoteDtoRequest)).thenReturn(quoteRequest);

        when(factory.makeQuoteService(quoteRequest)).thenReturn(newQuoteService);

        doNothing().when(newQuoteService).request();

        when(newQuoteService.getQuote()).thenReturn(quoteResponse);
        when(quoteDtoConverter.convertToDto(quoteResponse)).thenReturn(quoteDtoResponse);
//        when(quoteResponse.getClientId()).thenReturn(clientId);
        when(thinClientDtoConverter.convertToDto(client)).thenReturn(thinClientDto);

        when(newQuoteService.getAvailableOperations()).thenReturn(Arrays.asList("operations"));

        String request = objectMapper.writeValueAsString(requestQuoteDto);

        MvcResult result = this.mockMvc.perform(post(Uris.QUOTES + Uris.REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isCreated())
                .andReturn();


        String body = result.getResponse().getContentAsString();
        assertThat(objectMapper.readValue(body, QuoteDto.class)).isEqualTo(quoteDtoResponse);

    }

    @Test
    public void createQuote_QuoteDto_ReturnsUpdatedQuoteDto() throws Exception {
        when(quoteDtoConverter.convertToEntity(quoteDtoRequest)).thenReturn(quoteRequest);
        when(clientService.read(quoteRequest.getClient().getId())).thenReturn(client);
        when(factory.makeQuoteService(quoteRequest)).thenReturn(requestedQuoteService);
        doNothing().when(requestedQuoteService).create();
        when(requestedQuoteService.getQuote()).thenReturn(quoteResponse);
        when(quoteDtoConverter.convertToDto(quoteResponse)).thenReturn(quoteDtoResponse);
        when(thinClientDtoConverter.convertToDto(client)).thenReturn(thinClientDto);
        when(requestedQuoteService.getAvailableOperations()).thenReturn(Arrays.asList("operations"));

        MvcResult result = this.mockMvc.perform(post(Uris.QUOTES + Uris.CREATE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(quoteDtoRequest)))
                .andExpect(status().isCreated())
                .andReturn();


        String body = result.getResponse().getContentAsString();
        assertThat(objectMapper.readValue(body, QuoteDto.class)).isEqualTo(quoteDtoResponse);
    }

    @Test
    public void cancelQuotedQuote_QuoteDto_ReturnsUpdatedQuoteDto() throws Exception {
        when(quoteDtoConverter.convertToEntity(quoteDtoRequest)).thenReturn(quoteRequest);
        when(clientService.read(quoteRequest.getClient().getId())).thenReturn(client);
        when(factory.makeQuoteService(quoteRequest)).thenReturn(quotedQuoteService);
        doNothing().when(quotedQuoteService).cancel();
        when(quotedQuoteService.getQuote()).thenReturn(quoteResponse);
        when(quoteDtoConverter.convertToDto(quoteResponse)).thenReturn(quoteDtoResponse);
        when(thinClientDtoConverter.convertToDto(client)).thenReturn(thinClientDto);
        when(quotedQuoteService.getAvailableOperations()).thenReturn(Arrays.asList("operations"));

        MvcResult result = this.mockMvc.perform(post(Uris.QUOTES + Uris.CANCEL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(quoteDtoRequest)))
                .andExpect(status().isCreated())
                .andReturn();


        String body = result.getResponse().getContentAsString();
        assertThat(objectMapper.readValue(body, QuoteDto.class)).isEqualTo(quoteDtoResponse);
    }



    @Test
    public void modifyQuotedQuote_QuoteDto_ReturnsUpdatedQuoteDto() throws Exception {
        when(quoteDtoConverter.convertToEntity(quoteDtoRequest)).thenReturn(quoteRequest);
        when(clientService.read(quoteRequest.getClient().getId())).thenReturn(client);
        when(factory.makeQuoteService(quoteRequest)).thenReturn(quotedQuoteService);
        doNothing().when(quotedQuoteService).modify();
        when(quotedQuoteService.getQuote()).thenReturn(quoteResponse);
        when(quoteDtoConverter.convertToDto(quoteResponse)).thenReturn(quoteDtoResponse);
//        doNothing().when(quoteResponse).getClientId();
        Client client1 = quoteResponse.getClient();
        assertThat(client1).isNotNull();
        when(thinClientDtoConverter.convertToDto(client1)).thenReturn(thinClientDto);
        when(quotedQuoteService.getAvailableOperations()).thenReturn(Arrays.asList("operations"));

        MvcResult result = this.mockMvc.perform(post(Uris.QUOTES + Uris.MODIFY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(quoteDtoRequest)))
                .andExpect(status().isCreated())
                .andReturn();


        String body = result.getResponse().getContentAsString();
        assertThat(objectMapper.readValue(body, QuoteDto.class)).isEqualTo(quoteDtoResponse);
    }


    @Test
    public void sendQuotedQuote_QuoteDto_ReturnsUpdatedQuoteDto() throws Exception {
        when(quoteDtoConverter.convertToEntity(quoteDtoRequest)).thenReturn(quoteRequest);
        when(clientService.read(quoteRequest.getClient().getId())).thenReturn(client);
        when(factory.makeQuoteService(quoteRequest)).thenReturn(quotedQuoteService);
        doNothing().when(quotedQuoteService).send();
        when(quotedQuoteService.getQuote()).thenReturn(quoteResponse);
        when(quoteDtoConverter.convertToDto(quoteResponse)).thenReturn(quoteDtoResponse);
        when(thinClientDtoConverter.convertToDto(client)).thenReturn(thinClientDto);
        when(quotedQuoteService.getAvailableOperations()).thenReturn(Arrays.asList("operations"));

        MvcResult result = this.mockMvc.perform(post(Uris.QUOTES + Uris.SEND)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(quoteDtoRequest)))
                .andExpect(status().isCreated())
                .andReturn();


        String body = result.getResponse().getContentAsString();
        assertThat(objectMapper.readValue(body, QuoteDto.class)).isEqualTo(quoteDtoResponse);
    }



    @Test
    public void acceptQuote() throws Exception {
        when(quoteDtoConverter.convertToEntity(quoteDtoRequest)).thenReturn(quoteRequest);
        when(clientService.read(quoteRequest.getClient().getId())).thenReturn(client);
        when(factory.makeQuoteService(quoteRequest)).thenReturn(waitingResponseQuoteService);
        doNothing().when(waitingResponseQuoteService).accept();
        when(waitingResponseQuoteService.getQuote()).thenReturn(quoteResponse);
        when(saleController.createFromQuote(quoteResponse)).thenReturn(saleDtoResponse);

        MvcResult result = this.mockMvc.perform(post(Uris.QUOTES + Uris.ACCEPT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(quoteDtoRequest)))
                .andExpect(status().isCreated())
                .andReturn();


        String body = result.getResponse().getContentAsString();
        assertThat(objectMapper.readValue(body, SaleDto.class)).isEqualTo(saleDtoResponse);
    }

    @Test
    public void rejectQuote() throws Exception {
        when(quoteDtoConverter.convertToEntity(quoteDtoRequest)).thenReturn(quoteRequest);
        when(clientService.read(quoteRequest.getClient().getId())).thenReturn(client);
        when(factory.makeQuoteService(quoteRequest)).thenReturn(waitingResponseQuoteService);
        doNothing().when(quotedQuoteService).reject();
        when(waitingResponseQuoteService.getQuote()).thenReturn(quoteResponse);
        when(quoteDtoConverter.convertToDto(quoteResponse)).thenReturn(quoteDtoResponse);
        when(thinClientDtoConverter.convertToDto(client)).thenReturn(thinClientDto);
        when(waitingResponseQuoteService.getAvailableOperations()).thenReturn(Arrays.asList("operations"));

        MvcResult result = this.mockMvc.perform(post(Uris.QUOTES + Uris.REJECT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(quoteDtoRequest)))
                .andExpect(status().isCreated())
                .andReturn();


        String body = result.getResponse().getContentAsString();
        assertThat(objectMapper.readValue(body, QuoteDto.class)).isEqualTo(quoteDtoResponse);
    }


}