package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface QuoteService {
    Quote createQuoteFromRequest(Quote requestQuote);
    Quote updateQuote(Quote quote, QuoteState quoteState);
    List<Quote> getNewestQuotes(int quantity);
    Long findLastQuoteNumber();
}
