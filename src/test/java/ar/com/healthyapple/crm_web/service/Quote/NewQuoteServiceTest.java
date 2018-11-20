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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class NewQuoteServiceTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @MockBean
    private Quote quote;

    @MockBean
    private QuoteService quoteService;

    @MockBean
    private QuoteListService quoteListService;

    @MockBean
    private QuoteOperationsFacade operations;

    private NewQuoteService newQuoteStateBasedService;

    @Before
    public void setUp() throws Exception {
        newQuoteStateBasedService = new NewQuoteService(operations);
    }

    @Test
    public void isResponsibleFor() {
        List<QuoteState> notResponsibleFor = new ArrayList<>(Arrays.asList(
                QuoteState.MODIFIED,
                QuoteState.REQUESTED,
                QuoteState.CANCELED,
                QuoteState.QUOTED,
                QuoteState.REJECTED,
                QuoteState.NEW,
                QuoteState.WAITING_RESPONSE,
                QuoteState.ACCEPTED
        ));

        notResponsibleFor.forEach(state -> assertThat(newQuoteStateBasedService.isResponsibleFor(state)).isFalse());
        assertThat(newQuoteStateBasedService.isResponsibleFor(QuoteState.ASK)).isTrue();
    }

    @Test
    public void isResponsibleForWithNull() {
        exception.expect(AssertionError.class);
        newQuoteStateBasedService.isResponsibleFor(null);
    }


    @Test
    public void request() {
        Quote savedQuote = Mockito.mock(Quote.class);
        QuoteList quoteList = Mockito.mock(QuoteList.class);
        newQuoteStateBasedService.setQuote(quote);

        doNothing().when(operations).request(quote);
        when(operations.getQuote()).thenReturn(savedQuote);
        newQuoteStateBasedService.request();
        Quote result = newQuoteStateBasedService.getQuote();
        assertThat(result).isEqualTo(savedQuote);
    }

    @Test
    public void requestWithNull() {
        exception.expect(AssertionError.class);
        Quote savedQuote = Mockito.mock(Quote.class);
        QuoteList quoteList = Mockito.mock(QuoteList.class);
        newQuoteStateBasedService.setQuote(null);

        doThrow(AssertionError.class).when(operations).request(quote);
        newQuoteStateBasedService.request();

    }

    @Test
    public void createThrowsQuoteOperationNotAllowed() {
        exception.expect(QuoteOperationNotAllowedException.class);

        Quote quote = Mockito.mock(Quote.class);
        newQuoteStateBasedService.setQuote(quote);
        newQuoteStateBasedService.create();
    }



    @Test
    public void cancelThrowsQuoteOperationNotAllowed() {
        exception.expect(QuoteOperationNotAllowedException.class);

        Quote quote = Mockito.mock(Quote.class);
        newQuoteStateBasedService.setQuote(quote);
        newQuoteStateBasedService.cancel();
    }

    @Test
    public void modifyThrowsQuoteOperationNotAllowed() {
        exception.expect(QuoteOperationNotAllowedException.class);

        Quote quote = Mockito.mock(Quote.class);
        newQuoteStateBasedService.setQuote(quote);
        newQuoteStateBasedService.modify();
    }

    @Test
    public void rejectThrowsQuoteOperationNotAllowed() {
        exception.expect(QuoteOperationNotAllowedException.class);

        Quote quote = Mockito.mock(Quote.class);
        newQuoteStateBasedService.setQuote(quote);
        newQuoteStateBasedService.reject();
    }

    @Test
    public void acceptThrowsQuoteOperationNotAllowed() {
        exception.expect(QuoteOperationNotAllowedException.class);

        Quote quote = Mockito.mock(Quote.class);
        newQuoteStateBasedService.setQuote(quote);
        newQuoteStateBasedService.accept();
    }

    @Test
    public void sendThrowsQuoteOperationNotAllowed() {
        exception.expect(QuoteOperationNotAllowedException.class);

        Quote quote = Mockito.mock(Quote.class);
        newQuoteStateBasedService.setQuote(quote);
        newQuoteStateBasedService.send();
    }
}