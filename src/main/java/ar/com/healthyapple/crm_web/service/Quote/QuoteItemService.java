package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteItem;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public interface QuoteItemService {
    QuoteItem create(QuoteItem quoteItem);
}
