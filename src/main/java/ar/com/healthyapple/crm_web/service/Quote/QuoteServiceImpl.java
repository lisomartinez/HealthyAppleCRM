package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.model.Product.StateBasedProduct;
import ar.com.healthyapple.crm_web.model.Quote.QQuote;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;
import ar.com.healthyapple.crm_web.repository.Quote.QuoteRepository;
import com.querydsl.core.types.dsl.NumberExpression;
import javafx.beans.binding.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuoteServiceImpl implements QuoteService {

    private Quote quote;

    private QuoteRepository quoteRepository;

    @Autowired
    public QuoteServiceImpl(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    private Quote getUpdatedQuote() {
        return quote;
    }

    @Override
    public Quote createQuoteFromRequest(Quote requestQuote) {
        assert requestQuote != null : "Quote can not be null";

        setupQuote(requestQuote);
        return quoteRepository.save(requestQuote);
    }

    private void setupQuote(Quote quote) {
        quote.setVersion(0);
        Long number = quoteRepository.findLastQuoteNumber();

        quote.setNumber(number != null ? ++number : 0);
        quote.setPrice(BigDecimal.valueOf(0));
        quote.setCost(BigDecimal.valueOf(0));
        quote.setStatus(QuoteState.REQUESTED);
        quote.setCreated(LocalDateTime.now());
    }

    @Override
    public Quote updateQuote(Quote quoteToUpdate, QuoteState quoteState) {
        assert quoteToUpdate != null : "Quote can not be null";

        clone(quoteToUpdate);
        increaseVersion();
        updateState(quoteState);

        return quoteRepository.save(getUpdatedQuote());
    }

    private void clone(Quote quoteToClone) {
        Quote cloned = new Quote();
        cloned.setId(null);
        cloned.setNumber(quoteToClone.getNumber());
        cloned.setDescription(quoteToClone.getDescription());
        cloned.setCreated(LocalDateTime.now());
        cloned.setCost(quoteToClone.getCost());
        cloned.setPrice(quoteToClone.getPrice());
        cloned.setClient(quoteToClone.getClient());
        List<StateBasedProduct> productList = new ArrayList<>(quoteToClone.getProducts());

        cloned.setProducts(productList);
        cloned.setVersion(quoteToClone.getVersion());
        cloned.setStatus(quoteToClone.getStatus());

        quote = cloned;
    }

    private void increaseVersion() {
        quote.setVersion(quote.getVersion() + 1);
    }

    private void updateState( QuoteState quoteState) {
        quote.setStatus(quoteState);
    }

    @Override
    public List<Quote> getNewestQuotes(int quantity) {
        return quoteRepository.getNewestQuotes(5);
    }

    @Override
    public Long findLastQuoteNumber() {
        return quoteRepository.findLastQuoteNumber();
    }
}
