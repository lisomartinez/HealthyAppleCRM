package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.exceptions.QuoteOperationNotAllowedException;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;
import ar.com.healthyapple.crm_web.repository.Quote.QuoteListRepository;
import ar.com.healthyapple.crm_web.repository.Quote.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class RequestedQuoteStateBaseService implements QuoteStateBasedService {

    private Quote quote;

    private QuoteRepository quoteRepository;

    private QuoteListRepository quoteListRepository;

    private QuoteService quoteService;

    private QuoteListService quoteListService;

    @Autowired
    public RequestedQuoteStateBaseService(QuoteRepository quoteRepository, QuoteListRepository quoteListRepository, QuoteService quoteService, QuoteListService quoteListService) {
        this.quoteRepository = quoteRepository;
        this.quoteListRepository = quoteListRepository;
        this.quoteService = quoteService;
        this.quoteListService = quoteListService;
    }

    public RequestedQuoteStateBaseService() {
    }

    @Override
    public boolean isResponsibleFor(QuoteState quoteState) {
        return quoteState.equals(QuoteState.REQUESTED);
    }

    @Override
    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    @Override
    public Quote create(Quote newQuote) throws QuoteOperationNotAllowedException {

        Quote savedNewQuote = quoteService.updateQuote(newQuote, QuoteState.NEW);

        quoteListService.addQuoteToQuoteList(quote);

        return savedNewQuote;
    }

    @Override
    public Quote cancel(Quote newQuote) throws QuoteOperationNotAllowedException {
        Quote savedQuote = quoteService.updateQuote(quote, QuoteState.CANCELED);
        quoteListService.addQuoteToQuoteList(savedQuote);
        return savedQuote;
    }


}