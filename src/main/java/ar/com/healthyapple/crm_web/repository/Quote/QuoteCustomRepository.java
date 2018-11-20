package ar.com.healthyapple.crm_web.repository.Quote;

import ar.com.healthyapple.crm_web.model.Quote.Quote;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteCustomRepository {
    List<Quote> getNewestQuotes(int quantity);
    Long findLastQuoteNumber();
}
