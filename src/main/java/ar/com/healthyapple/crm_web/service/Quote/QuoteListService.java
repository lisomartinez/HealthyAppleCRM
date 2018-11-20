package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.exceptions.QuoteListNotFoundException;
import ar.com.healthyapple.crm_web.exceptions.QuoteNotFoundException;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteList;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public interface QuoteListService {

    QuoteList addQuoteToQuoteList(Quote quote) throws QuoteListNotFoundException;
    QuoteList createQuoteList(Quote quote);
    boolean isQuoteTheLastVersionInList(Quote quote) throws QuoteNotFoundException, QuoteListNotFoundException;

    Optional<QuoteList> read(Long number);
}
