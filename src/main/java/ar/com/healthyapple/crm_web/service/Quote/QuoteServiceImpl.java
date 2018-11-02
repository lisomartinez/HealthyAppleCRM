package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;
import ar.com.healthyapple.crm_web.repository.Quote.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class QuoteServiceImpl implements QuoteService {

    private Quote quote;

    private QuoteRepository quoteRepository;

    @Autowired
    public QuoteServiceImpl(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Override
    public void clone(Quote quoteToClone) {
        quote = cloneHelper(quoteToClone);
        quote.setVersion(quoteToClone.getVersion());
        quote.setStatus(quoteToClone.getStatus());
    }

    @Override
    public void updateState( QuoteState quoteState) {
        quote.setStatus(quoteState);
     }

    @Override
    public void updateVersion() {
        quote.setVersion(quote.getVersion() + 1);
    }

    private Quote cloneHelper(Quote quote) {
        Quote cloned = new Quote();
        cloned.setId(null);
        cloned.setNumber(quote.getNumber());
        cloned.setDescription(quote.getDescription());
        cloned.setCreated(LocalDateTime.now());
        cloned.setCost(quote.getCost());
        cloned.setPrice(quote.getPrice());
        cloned.setClient(quote.getClient());
        cloned.setProductServices(quote.getProductServices());
        return cloned;
    }

    @Override
    public Quote getUpdatedQuote() {
        return quote;
    }

    @Override
    public Quote updateQuote(Quote quote, QuoteState quoteState) {
        clone(quote);
        updateVersion();
        updateState(quoteState);

        return quoteRepository.save(getUpdatedQuote());
    }
}
