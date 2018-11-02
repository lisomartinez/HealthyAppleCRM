package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.dto.quote.QuoteDto;
import ar.com.healthyapple.crm_web.dto.quote.RequestQuoteDto;
import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.QuoteOperationNotAllowedException;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.service.Quote.QuoteStateBasedService;
import ar.com.healthyapple.crm_web.service.Quote.QuoteStateBasedServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Uris.QUOTES)
public class QuoteController {

    private QuoteStateBasedServiceFactory quoteStateBasedServiceFactory;

    private EntityDtoConverter entityDtoConverter;

    @Autowired
    public QuoteController(QuoteStateBasedServiceFactory quoteStateBasedServiceFactory, EntityDtoConverter entityDtoConverter) {
        this.quoteStateBasedServiceFactory = quoteStateBasedServiceFactory;
        this.entityDtoConverter = entityDtoConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuoteDto requestQuote(@RequestBody RequestQuoteDto requestQuoteDto) throws QuoteOperationNotAllowedException, AlreadyExistException {
        QuoteStateBasedService quoteService = quoteStateBasedServiceFactory.makeQuoteService(entityDtoConverter.convertToEntity(requestQuoteDto, Quote.class));
        return entityDtoConverter.convertToDto(quoteService.request(), QuoteDto.class);
    }
}
