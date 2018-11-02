package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteList;
import org.springframework.stereotype.Service;

@Service
public interface QuoteListService {

    QuoteList addQuoteToQuoteList(Quote quote);
    boolean isQuoteTheLastVersionInList(Quote quote);
}
