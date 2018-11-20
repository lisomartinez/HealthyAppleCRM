package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Product.Product;
import ar.com.healthyapple.crm_web.model.Product.ProductState;
import ar.com.healthyapple.crm_web.model.Product.StateBasedProduct;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteItem;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;
import ar.com.healthyapple.crm_web.model.Sale.SaleItem;
import ar.com.healthyapple.crm_web.repository.Quote.QuoteRepository;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class QuoteServiceTest {

    private final static Long NUMBER = 1L;
    private final static Integer VERSION = 1;
    private final static String DESCRIPTION = "Quote description";
    private final static LocalDateTime CREATED = LocalDateTime.now();
    private final static BigDecimal COST = BigDecimal.valueOf(1000);
    private final static BigDecimal PRICE = BigDecimal.valueOf(500);


    @Rule
    public ExpectedException exception = ExpectedException.none();

    @TestConfiguration
    static class QuoteServiceTestContextConfiguration {

        @Bean
        public QuoteService quoteService(QuoteRepository quoteRepository) {
            return new QuoteServiceImpl(quoteRepository);
        }
    }

    @Autowired
    private QuoteService quoteService;

    @MockBean
    private QuoteRepository quoteRepository;

    @MockBean
    private Client client;

    @MockBean
    private Product product;

    @MockBean
    private QuoteItem quoteItem;

    private Quote quote;

    @BeforeEach
    public void setUp() throws Exception {
        StateBasedProduct stateBasedProduct = Mockito.mock(StateBasedProduct.class);
        List<StateBasedProduct> products = new ArrayList<>(Arrays.asList(stateBasedProduct));
        List<QuoteItem> quoteItems = new ArrayList<>(Arrays.asList(quoteItem));
        quote = new Quote(
                NUMBER,
                VERSION,
                DESCRIPTION,
                QuoteState.NEW,
                CREATED,
                COST,
                PRICE,
                client,
                products,
                quoteItems
        );
    }

    @Test
    void createQuoteFromRequest_RequestQuote_returnSavedQuote() {
        when(quoteRepository.save(quote)).thenReturn(quote);
        Quote result = quoteService.createQuoteFromRequest(quote);
        assertThat(result).isEqualTo(quote);
    }

    @Test
    void createQuoteFromRequest_NullRequestQuote_ThrowsAssertionError() {
        assertThrows(AssertionError.class, () -> quoteService.createQuoteFromRequest(null));
    }

    @Test
    public void updatedQuote() {
        StateBasedProduct stateBasedProduct = Mockito.mock(StateBasedProduct.class);
        List<StateBasedProduct> products = new ArrayList<>(Arrays.asList(stateBasedProduct));
        List<QuoteItem> quoteItems = new ArrayList<>(Arrays.asList(quoteItem));

        Quote savedQuote = new Quote(
                NUMBER + 1,
                VERSION + 1,
                DESCRIPTION,
                QuoteState.MODIFIED,
                CREATED,
                COST,
                PRICE,
                client,
                products,
                quoteItems
        );

        when(quoteRepository.save(quote)).thenReturn(savedQuote);

        Quote updatedQuote = quoteService.updateQuote(quote, QuoteState.MODIFIED);
        assertThat(updatedQuote).isEqualTo(savedQuote);
    }

    @Test
    public void updatedQuoteWithNull() {
        assertThrows(AssertionError.class, () -> quoteService.updateQuote(null, QuoteState.MODIFIED));
    }

    @Test
    public void getNewestQuotes() {

    }

}