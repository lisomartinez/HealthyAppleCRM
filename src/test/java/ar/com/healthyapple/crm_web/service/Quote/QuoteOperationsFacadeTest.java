package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.Utils.QuoteFactory;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteList;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;
import ar.com.healthyapple.crm_web.service.Product.ProductService;
import ar.com.healthyapple.crm_web.service.Product.StateBasedProductService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class QuoteOperationsFacadeTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @MockBean
    private QuoteService quoteService;

    @MockBean
    private QuoteListService quoteListService;

    @MockBean
    private ProductService productService;

    @MockBean
    private StateBasedProductService stateBasedProductService;

    @MockBean
    private QuoteItemService quoteItemService;

    private QuoteOperationsFacade operations;


    @Before
    public void setUp() throws Exception {
        operations = new QuoteOperationsFacadeImpl(quoteService, quoteListService, productService, stateBasedProductService, quoteItemService);
    }

    @Test
    public void request_WellFormedQuote_ReturnsCreatedQuote() {
        Quote quote = QuoteFactory.makeAskQuote();

        Quote savedQuote = QuoteFactory.makeAskQuote();
        savedQuote.setVersion(0);
        savedQuote.setPrice(BigDecimal.valueOf(0));
        savedQuote.setCost(BigDecimal.valueOf(0));
        savedQuote.setStatus(QuoteState.REQUESTED);

        QuoteList quoteList = new QuoteList();
        quoteList.setId(1L);
        quoteList.setQuoteNumber(savedQuote.getNumber());
        quoteList.setQuotes(Arrays.asList(savedQuote));

        when(quoteService.createQuoteFromRequest(quote)).thenReturn(savedQuote);
        when(quoteListService.createQuoteList(savedQuote)).thenReturn(quoteList);

        operations.request(quote);
        Quote result = operations.getQuote();
        assertThat(result).isEqualTo(savedQuote);
    }

    @Test
    public void request_WithNull_ThrowAssertionError() {
        Quote quote = null;
        exception.expect(AssertionError.class);
        operations.request(quote);
    }

    @Test
    public void create() {
    }

    @Test
    public void create_WithNull_ThrowAssertionError() {
        Quote quote = null;
        exception.expect(AssertionError.class);
        operations.create(quote);
    }

    @Test
    public void cancel() {
    }

    @Test
    public void cancelWithNullThrowAssertionError() {
        Quote quote = null;
        exception.expect(AssertionError.class);
        operations.cancel(quote);
    }

    @Test
    public void modify() {
    }

    @Test
    public void modifyWithNullThrowAssertionError() {
        Quote quote = null;
        exception.expect(AssertionError.class);
        operations.modify(quote);
    }

    @Test
    public void reject() {
    }

    @Test
    public void rejectWithNullThrowAssertionError() {
        Quote quote = null;
        exception.expect(AssertionError.class);
        operations.reject(quote);

    }

    @Test
    public void accept() {
    }

    @Test
    public void acceptWithNullThrowAssertionError() {
        Quote quote = null;
        exception.expect(AssertionError.class);
        operations.accept(quote);
    }
}