package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.exceptions.QuoteOperationNotAllowedException;
import ar.com.healthyapple.crm_web.exceptions.QuoteVersionMismatchException;
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
public class WaitingResponseQuoteServiceTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @MockBean
    private QuoteOperationsFacade operations;

    private WaitingResponseQuoteService waitingResponseQuoteService;

    @Before
    public void setUp() throws Exception {
        waitingResponseQuoteService = new WaitingResponseQuoteService(operations);
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
                QuoteState.REQUESTED,
                QuoteState.ASK
        ));

        notResponsibleFor.forEach(state -> assertThat(waitingResponseQuoteService.isResponsibleFor(state)).isFalse());

        assertThat(waitingResponseQuoteService.isResponsibleFor(QuoteState.WAITING_RESPONSE)).isTrue();
    }


    @Test
    public void modifyLastQuote() {
        Quote quote = Mockito.mock(Quote.class);
        Quote savedQuote = Mockito.mock(Quote.class);
        waitingResponseQuoteService.setQuote(quote);
        doNothing().when(operations).modify(quote);
        when(operations.getQuote()).thenReturn(savedQuote);
        waitingResponseQuoteService.modify();

        Quote result = waitingResponseQuoteService.getQuote();
        assertThat(result).isEqualTo(savedQuote);
    }

    @Test
    public void modifyPrevQuoteShouldThrowException() {
        exception.expect(QuoteVersionMismatchException.class);
        Quote quote = Mockito.mock(Quote.class);

        waitingResponseQuoteService.setQuote(quote);
        doThrow(QuoteVersionMismatchException.class).when(operations).modify(quote);
        waitingResponseQuoteService.modify();
    }

    @Test
    public void reject() {
        Quote quote = Mockito.mock(Quote.class);
        Quote savedQuote = Mockito.mock(Quote.class);
        QuoteList quoteList = Mockito.mock(QuoteList.class);
        waitingResponseQuoteService.setQuote(quote);
        doNothing().when(operations).reject(quote);
        when(operations.getQuote()).thenReturn(savedQuote);
        waitingResponseQuoteService.reject();
        Quote result = waitingResponseQuoteService.getQuote();
        assertThat(result).isEqualTo(savedQuote);
    }

    @Test
    public void accept() {
        Quote quote = Mockito.mock(Quote.class);
        Quote savedQuote = Mockito.mock(Quote.class);
        waitingResponseQuoteService.setQuote(quote);
        doNothing().when(operations).accept(quote);
        when(operations.getQuote()).thenReturn(savedQuote);
        waitingResponseQuoteService.accept();
        Quote result = waitingResponseQuoteService.getQuote();
        assertThat(result).isEqualTo(savedQuote);
    }

    @Test
    public void cancelThrowsQuoteOperationNotAllowed() {
        exception.expect(QuoteOperationNotAllowedException.class);

        Quote quote = Mockito.mock(Quote.class);
        waitingResponseQuoteService.setQuote(quote);
        waitingResponseQuoteService.cancel();
    }

    @Test
    public void createThrowsQuoteOperationNotAllowed() {
        exception.expect(QuoteOperationNotAllowedException.class);

        Quote quote = Mockito.mock(Quote.class);
        waitingResponseQuoteService.setQuote(quote);
        waitingResponseQuoteService.create();
    }


    @Test
    public void sendThrowsQuoteOperationNotAllowed() {
        exception.expect(QuoteOperationNotAllowedException.class);

        Quote quote = Mockito.mock(Quote.class);
        waitingResponseQuoteService.setQuote(quote);
        waitingResponseQuoteService.send();
    }
}