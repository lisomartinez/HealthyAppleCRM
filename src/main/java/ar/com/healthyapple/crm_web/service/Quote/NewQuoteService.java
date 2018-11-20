package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.exceptions.*;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class NewQuoteService implements QuoteStateBasedService {

    private final static Integer FIRST = 1;

    private Quote quote;

    private Quote updatedQuote;

    private QuoteOperationsFacade operations;

    public NewQuoteService(QuoteOperationsFacade operations) {
        this.operations = operations;
    }

    @Override
    public boolean isResponsibleFor(QuoteState quoteState) {
        assert quoteState != null : "State's quote could not be null";
        return quoteState.equals(QuoteState.ASK);
    }

    @Override
    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    @Override
    public void request() throws QuoteOperationNotAllowedException, AlreadyExistException {
        assert this.quote != null : "Quote could not be null";
        operations.request(this.quote);
        this.updatedQuote = operations.getQuote();
    }

    @Override
    public List<String> getAvailableOperations() {
        return Arrays.asList("create, cancel");
    }

    @Override
    public Quote getQuote() {
        return updatedQuote;
    }

}
