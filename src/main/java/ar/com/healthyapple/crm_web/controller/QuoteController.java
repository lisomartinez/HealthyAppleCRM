package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.controller.DtoConverter.QuoteDtoConverter;
import ar.com.healthyapple.crm_web.controller.DtoConverter.RequestQuoteDtoConverter;
import ar.com.healthyapple.crm_web.controller.DtoConverter.ThinClientDtoConverter;
import ar.com.healthyapple.crm_web.dto.Client.ThinClientDto;
import ar.com.healthyapple.crm_web.dto.Sale.SaleDto;
import ar.com.healthyapple.crm_web.dto.quote.QuoteDto;
import ar.com.healthyapple.crm_web.dto.quote.RequestQuoteDto;
import ar.com.healthyapple.crm_web.exceptions.*;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;
import ar.com.healthyapple.crm_web.service.Client.ClientService;
import ar.com.healthyapple.crm_web.service.Quote.QuoteStateBasedService;
import ar.com.healthyapple.crm_web.service.Quote.QuoteStateBasedServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Uris.QUOTES)
public class QuoteController {

    private QuoteStateBasedServiceFactory quoteStateBasedServiceFactory;

    private QuoteDtoConverter quoteDtoConverter;

    private RequestQuoteDtoConverter requestQuoteDtoConverter;

    private ClientService clientService;

    private ThinClientDtoConverter thinClientDtoConverter;

    private SaleController saleController;

    @Autowired
    public QuoteController(QuoteStateBasedServiceFactory quoteStateBasedServiceFactory, QuoteDtoConverter quoteDtoConverter, RequestQuoteDtoConverter requestQuoteDtoConverter, ClientService clientService, ThinClientDtoConverter thinClientDtoConverter, SaleController saleController) {
        this.quoteStateBasedServiceFactory = quoteStateBasedServiceFactory;
        this.quoteDtoConverter = quoteDtoConverter;
        this.requestQuoteDtoConverter = requestQuoteDtoConverter;
        this.clientService = clientService;
        this.thinClientDtoConverter = thinClientDtoConverter;
        this.saleController = saleController;
    }

    @PostMapping(Uris.REQUEST)
    @ResponseStatus(HttpStatus.CREATED)
    public QuoteDto requestQuote(@RequestBody RequestQuoteDto requestQuoteDto) throws QuoteOperationNotAllowedException, AlreadyExistException {
        QuoteStateBasedService quoteService = getResponsibleServiceFor(MakeQuoteDtoFrom(requestQuoteDto));
        quoteService.request();
        return makeResponse(quoteService.getQuote(), quoteService);
    }

    private QuoteDto MakeQuoteDtoFrom(RequestQuoteDto requestQuoteDto) {
        QuoteDto quoteDto = requestQuoteDtoConverter.convertToQuoteDto(requestQuoteDto);
        quoteDto.setStatus(QuoteState.ASK);
        return quoteDto;
    }

    private QuoteStateBasedService getResponsibleServiceFor(QuoteDto quoteDto) throws QuoteOperationNotAllowedException {
       Quote quote = quoteDtoConverter.convertToEntity(quoteDto);
       quote.setClient(getClientFrom(quoteDto.getClientId()));
       return quoteStateBasedServiceFactory.makeQuoteService(quote);
    }

    private Client getClientFrom(Long id) throws NotFoundException {
        return clientService.read(id);
    }

    private QuoteDto makeResponse(Quote quote, QuoteStateBasedService quoteService) {
        QuoteDto response = quoteDtoConverter.convertToDto(quote);

        Client client = quote.getClient();
        ThinClientDto clientDto = getThinClientDtoFrom(client);
        response.setClientId(clientDto.getId());

        List<String> availableOperations = quoteService.getAvailableOperations();
        response.setAvailableOperations(availableOperations);

        return response;
    }

    private ThinClientDto getThinClientDtoFrom(Client client) throws NotFoundException {
        return thinClientDtoConverter.convertToDto(client);
    }

    @PostMapping(Uris.CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    public QuoteDto createQuote(@RequestBody QuoteDto quoteDto) throws QuoteOperationNotAllowedException, QuoteNotFoundException, QuoteListNotFoundException {
        QuoteStateBasedService service =  getResponsibleServiceFor(quoteDto);
        service.create();
        return makeResponse(service.getQuote(), service);
    }

    @PostMapping(Uris.MODIFY)
    @ResponseStatus(HttpStatus.CREATED)
    public QuoteDto modifyQuote(@RequestBody QuoteDto quoteDto) throws QuoteOperationNotAllowedException, QuoteNotFoundException, QuoteListNotFoundException {
        QuoteStateBasedService service = getResponsibleServiceFor(quoteDto);
        service.modify();
        return makeResponse(service.getQuote(), service);
    }

    @PostMapping(Uris.CANCEL)
    @ResponseStatus(HttpStatus.CREATED)
    public QuoteDto cancelQuote(@RequestBody QuoteDto quoteDto) throws QuoteOperationNotAllowedException, QuoteNotFoundException, QuoteListNotFoundException {
        QuoteStateBasedService service =  getResponsibleServiceFor(quoteDto);
        service.cancel();
        return makeResponse(service.getQuote(), service);
    }

    @PostMapping(Uris.ACCEPT)
    @ResponseStatus(HttpStatus.CREATED)
    public SaleDto acceptQuote(@RequestBody QuoteDto quoteDto) throws QuoteOperationNotAllowedException, QuoteNotFoundException, QuoteListNotFoundException {
        QuoteStateBasedService service =  getResponsibleServiceFor(quoteDto);
        service.accept();
//        QuoteDto response = makeResponse(service.getQuote(), service);
        return saleController.createFromQuote(service.getQuote());
    }

    @PostMapping(Uris.REJECT)
    @ResponseStatus(HttpStatus.CREATED)
    public QuoteDto rejectQuote(@RequestBody QuoteDto quoteDto) throws QuoteOperationNotAllowedException, QuoteNotFoundException, QuoteListNotFoundException {
        QuoteStateBasedService service =  getResponsibleServiceFor(quoteDto);
        service.reject();
        return makeResponse(service.getQuote(), service);
    }

    @PostMapping(Uris.SEND)
    @ResponseStatus(HttpStatus.CREATED)
    public QuoteDto sendQuote(@RequestBody QuoteDto quoteDto) throws QuoteOperationNotAllowedException, QuoteNotFoundException, QuoteListNotFoundException {
        QuoteStateBasedService service =  getResponsibleServiceFor(quoteDto);
        service.send();
        return makeResponse(service.getQuote(), service);
    }



}
