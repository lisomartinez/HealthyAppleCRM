package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.exceptions.QuoteOperationNotAllowedException;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RequestedQuoteService implements QuoteStateBasedService {

    private Quote quote;

    private Quote updatedQuote;

    private QuoteOperationsFacade operations;

    @Autowired
    public RequestedQuoteService(QuoteOperationsFacade operations) {
        this.operations = operations;
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
    public void create() throws QuoteOperationNotAllowedException {
        assert quote != null : "Quote could not be null";
        operations.create(quote);
        this.updatedQuote = operations.getQuote();
    }

    @Override
    public void cancel() throws QuoteOperationNotAllowedException {
        assert quote != null : "Quote could not be null";
        operations.cancel(quote);
        this.updatedQuote = operations.getQuote();

    }

    @Override
    public List<String> getAvailableOperations() {
        return Arrays.asList("cancel", "create");
    }

    @Override
    public Quote getQuote() {
        return updatedQuote;
    }


}
