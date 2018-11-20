package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Sale.Sale;
import org.springframework.stereotype.Service;

@Service
public interface QuoteOperationsFacade {
    void request(Quote quote);
    void create(Quote quote);
    void cancel(Quote quote);
    void modify(Quote quote);
    void reject(Quote quote);
    void accept(Quote quote);
    void send(Quote quote);
    Quote getQuote();
}
