package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.QuoteNotFoundException;
import ar.com.healthyapple.crm_web.exceptions.QuoteOperationNotAllowedException;
import ar.com.healthyapple.crm_web.exceptions.QuoteVersionMismatchException;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;

@org.springframework.stereotype.Service
public interface QuoteStateBasedService {
//    Quote request(Quote quote) throws QuoteOperationNotAllowedException;
//

//
//    Quote modify(List<ProductService> services) throws QuoteOperationNotAllowedException;
//
//    void cancel() throws QuoteOperationNotAllowedException;
//
//    void confirm() throws QuoteOperationNotAllowedException;

    boolean isResponsibleFor(QuoteState quoteState);

    void setQuote(Quote quote);

    default Quote request() throws QuoteOperationNotAllowedException, AlreadyExistException {
        throw new QuoteOperationNotAllowedException("Operation Not Allowed");
    }

    default Quote create(Quote newQuote) throws QuoteOperationNotAllowedException {
        throw new QuoteOperationNotAllowedException("Operation Not Allowed");

    }
    default Quote cancel(Quote newQuote) throws QuoteOperationNotAllowedException {
        throw new QuoteOperationNotAllowedException("Operation Not Allowed");
    }

    default Quote modify(Quote newQuote) throws QuoteOperationNotAllowedException, QuoteNotFoundException, QuoteVersionMismatchException {
        throw new QuoteOperationNotAllowedException("Operation Not Allowed");
    }

    default Quote reject() throws QuoteOperationNotAllowedException {
        throw new QuoteOperationNotAllowedException("Operation Not Allowed");

    }

    default Quote accept() throws QuoteOperationNotAllowedException {
        throw new QuoteOperationNotAllowedException("Operation Not Allowed");

    }

    default Quote send() throws QuoteOperationNotAllowedException, QuoteNotFoundException {
        throw new QuoteOperationNotAllowedException("Operation Not Allowed");
    }
}
