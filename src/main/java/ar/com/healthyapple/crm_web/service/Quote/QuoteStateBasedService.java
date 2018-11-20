package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.QuoteNotFoundException;
import ar.com.healthyapple.crm_web.exceptions.QuoteOperationNotAllowedException;
import ar.com.healthyapple.crm_web.exceptions.QuoteVersionMismatchException;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuoteStateBasedService {
//    Quote request(Quote quote) throws QuoteOperationNotAllowedException;
//

//
//    Quote modify(List<QuoteItemDto> productServiceSaleList) throws QuoteOperationNotAllowedException;
//
//    void cancel() throws QuoteOperationNotAllowedException;
//
//    void confirm() throws QuoteOperationNotAllowedException;

    boolean isResponsibleFor(QuoteState quoteState);

    void setQuote(Quote quote);

    default void request() throws QuoteOperationNotAllowedException, AlreadyExistException {
        throw new QuoteOperationNotAllowedException("Operation Not Allowed");
    }

    default void create() throws QuoteOperationNotAllowedException {
        throw new QuoteOperationNotAllowedException("Operation Not Allowed");

    }
    default void cancel() throws QuoteOperationNotAllowedException {
        throw new QuoteOperationNotAllowedException("Operation Not Allowed");
    }

    default void modify() throws QuoteOperationNotAllowedException, QuoteNotFoundException, QuoteVersionMismatchException {
        throw new QuoteOperationNotAllowedException("Operation Not Allowed");
    }

    default void reject() throws QuoteOperationNotAllowedException {
        throw new QuoteOperationNotAllowedException("Operation Not Allowed");

    }

    default void accept() throws QuoteOperationNotAllowedException {
        throw new QuoteOperationNotAllowedException("Operation Not Allowed");

    }

    default void send() throws QuoteOperationNotAllowedException, QuoteNotFoundException {
        throw new QuoteOperationNotAllowedException("Operation Not Allowed");
    }

    Quote getQuote();

    List<String> getAvailableOperations();
}
