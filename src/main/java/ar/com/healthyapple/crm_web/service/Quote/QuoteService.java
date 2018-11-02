package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;
import org.springframework.stereotype.Service;

@Service
public interface QuoteService {

    void clone(Quote quote);

    void updateState(QuoteState quoteState);

    void updateVersion();

    Quote getUpdatedQuote();

    Quote updateQuote(Quote quote, QuoteState quoteState);
}
