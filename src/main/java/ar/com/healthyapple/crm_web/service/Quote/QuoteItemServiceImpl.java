package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.model.Quote.QuoteItem;
import ar.com.healthyapple.crm_web.repository.Quote.QuoteItemRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class QuoteItemServiceImpl implements QuoteItemService {

    private QuoteItemRepository quoteItemRepository;

    public QuoteItemServiceImpl(QuoteItemRepository quoteItemRepository) {
        this.quoteItemRepository = quoteItemRepository;
    }

    @Override
    public QuoteItem create(QuoteItem quoteItem) {
        return quoteItemRepository.save(quoteItem);
    }
}
