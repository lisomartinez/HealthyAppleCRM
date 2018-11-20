package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.exceptions.QuoteOperationNotAllowedException;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteList;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class RequestedQuoteServiceTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @MockBean
    private QuoteOperationsFacade operations;
    private RequestedQuoteService requestedQuoteService;

    @Before
    public void setUp() throws Exception {
        requestedQuoteService = new RequestedQuoteService(operations);
    }

    @Test
    public void isResponsibleFor() {
        List<QuoteState> notResponsibleFor = new ArrayList<>(Arrays.asList(
                QuoteState.MODIFIED,
                QuoteState.ACCEPTED,
                QuoteState.CANCELED,
                QuoteState.QUOTED,
                QuoteState.REJECTED,
                QuoteState.NEW,
                QuoteState.WAITING_RESPONSE,
                QuoteState.ASK
        ));

        notResponsibleFor.forEach(state -> assertThat(requestedQuoteService.isResponsibleFor(state)).isFalse());
        assertThat(requestedQuoteService.isResponsibleFor(QuoteState.REQUESTED)).isTrue();
    }

    @Test
    public void create() {
        Quote quote = Mockito.mock(Quote.class);
        Quote savedQuote = Mockito.mock(Quote.class);
        requestedQuoteService.setQuote(quote);

        doNothing().when(operations).create(quote);
        when(operations.getQuote()).thenReturn(savedQuote);
        requestedQuoteService.create();
        Quote result = requestedQuoteService.getQuote();
        assertThat(result).isEqualTo(savedQuote);

    }

    @Test
    public void createWithNull() {
        exception.expect(AssertionError.class);
        Quote quote = null;
        requestedQuoteService.setQuote(quote);
        requestedQuoteService.create();
    }

    @Test
    public void cancel() {
        Quote quote = Mockito.mock(Quote.class);
        Quote savedQuote = Mockito.mock(Quote.class);
        QuoteList quoteList = Mockito.mock(QuoteList.class);
        requestedQuoteService.setQuote(quote);


        doNothing().when(operations).cancel(quote);
        when(operations.getQuote()).thenReturn(savedQuote);
        requestedQuoteService.cancel();
        Quote result = requestedQuoteService.getQuote();
        assertThat(result).isEqualTo(savedQuote);
    }

    @Test
    public void cancelWithNull() {
        exception.expect(AssertionError.class);
        Quote quote = null;
        requestedQuoteService.setQuote(quote);
        requestedQuoteService.cancel();
    }


    @Test
    public void modifyThrowsQuoteOperationNotAllowed() {
        exception.expect(QuoteOperationNotAllowedException.class);

        Quote quote = Mockito.mock(Quote.class);
        requestedQuoteService.setQuote(quote);
        requestedQuoteService.modify();
    }

    @Test
    public void rejectThrowsQuoteOperationNotAllowed() {
        exception.expect(QuoteOperationNotAllowedException.class);

        Quote quote = Mockito.mock(Quote.class);
        requestedQuoteService.setQuote(quote);
        requestedQuoteService.reject();
    }

    @Test
    public void acceptThrowsQuoteOperationNotAllowed() {
        exception.expect(QuoteOperationNotAllowedException.class);

        Quote quote = Mockito.mock(Quote.class);
        requestedQuoteService.setQuote(quote);
        requestedQuoteService.accept();
    }

    @Test
    public void sendThrowsQuoteOperationNotAllowed() {
        exception.expect(QuoteOperationNotAllowedException.class);

        Quote quote = Mockito.mock(Quote.class);
        requestedQuoteService.setQuote(quote);
        requestedQuoteService.send();
    }

}