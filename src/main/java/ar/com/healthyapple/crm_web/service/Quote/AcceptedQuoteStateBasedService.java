package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.exceptions.QuoteNotFoundException;
import ar.com.healthyapple.crm_web.exceptions.QuoteOperationNotAllowedException;
import ar.com.healthyapple.crm_web.exceptions.QuoteVersionMismatchException;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;
import org.springframework.beans.factory.annotation.Autowired;


@org.springframework.stereotype.Service
public class AcceptedQuoteStateBasedService implements QuoteStateBasedService {

    private Quote quote;

    private QuoteService quoteService;

    private QuoteListService quoteListService;

    @Autowired
    public AcceptedQuoteStateBasedService(QuoteService quoteService, QuoteListService quoteListService) {
        this.quoteService = quoteService;
        this.quoteListService = quoteListService;
    }

    public AcceptedQuoteStateBasedService() {
    }

    @Override
    public boolean isResponsibleFor(QuoteState quoteState) {
        return quoteState.equals(QuoteState.ACCEPTED);
    }

    @Override
    public void setQuote(Quote quote) {
        this.quote = quote;
    }


    @Override
    public Quote cancel(Quote newQuote) throws QuoteOperationNotAllowedException {
        Quote savedQuote = quoteService.updateQuote(quote, QuoteState.CANCELED);
        quoteListService.addQuoteToQuoteList(savedQuote);
        return savedQuote;
    }

    @Override
    public Quote modify(Quote newQuote) throws QuoteOperationNotAllowedException, QuoteNotFoundException, QuoteVersionMismatchException {
        Quote savedQuote = quoteService.updateQuote(quote, QuoteState.MODIFIED);
        quoteListService.addQuoteToQuoteList(savedQuote);
        return savedQuote;
    }

    @Override
    public Quote send() throws QuoteOperationNotAllowedException, QuoteNotFoundException {
        Quote savedQuote = quoteService.updateQuote(quote, QuoteState.WAITING_RESPONSE);

        quoteListService.addQuoteToQuoteList(savedQuote);

        return savedQuote;
    }
}
