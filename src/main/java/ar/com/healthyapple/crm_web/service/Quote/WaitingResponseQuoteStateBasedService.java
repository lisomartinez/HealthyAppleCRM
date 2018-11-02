package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.exceptions.QuoteNotFoundException;
import ar.com.healthyapple.crm_web.exceptions.QuoteOperationNotAllowedException;
import ar.com.healthyapple.crm_web.exceptions.QuoteVersionMismatchException;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;
import ar.com.healthyapple.crm_web.repository.Quote.QuoteListRepository;
import ar.com.healthyapple.crm_web.repository.Quote.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class WaitingResponseQuoteStateBasedService implements QuoteStateBasedService {

    private Quote quote;

    private QuoteRepository quoteRepository;

    private QuoteListRepository quoteListRepository;

    private QuoteService quoteService;

    private QuoteListService quoteListService;

    @Autowired
    public WaitingResponseQuoteStateBasedService(QuoteRepository quoteRepository, QuoteListRepository quoteListRepository, QuoteService quoteService, QuoteListService quoteListService) {
        this.quoteRepository = quoteRepository;
        this.quoteListRepository = quoteListRepository;
        this.quoteService = quoteService;
        this.quoteListService = quoteListService;
    }

    @Override
    public boolean isResponsibleFor(QuoteState quoteState) {
        return quoteState.equals(QuoteState.WAITING_RESPONSE);
    }

    @Override
    public void setQuote(Quote quote) {
        this.quote = quote;
    }


    @Override
    public Quote modify(Quote newQuote) throws QuoteOperationNotAllowedException, QuoteNotFoundException, QuoteVersionMismatchException {

        if (quoteListService.isQuoteTheLastVersionInList(newQuote)) {

            Quote savedNewQuote = quoteService.updateQuote(newQuote, QuoteState.WAITING_RESPONSE);

            quoteListService.addQuoteToQuoteList(savedNewQuote);

            return savedNewQuote;
        } else {
            throw new QuoteVersionMismatchException("the quote is not the last version.");
        }
    }

    @Override
    public Quote reject() throws QuoteOperationNotAllowedException {

        Quote savedQuote = quoteService.updateQuote(quote, QuoteState.REJECTED);

        quoteListService.addQuoteToQuoteList(savedQuote);

        return savedQuote;
    }

    @Override
    public Quote accept() throws QuoteOperationNotAllowedException {

        Quote savedQuote = quoteService.updateQuote(quote, QuoteState.ACCEPTED);

        quoteListService.addQuoteToQuoteList(savedQuote);

        return savedQuote;
    }
}
