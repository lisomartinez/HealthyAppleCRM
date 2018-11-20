package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.exceptions.QuoteOperationNotAllowedException;
import ar.com.healthyapple.crm_web.exceptions.QuoteVersionMismatchException;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;
import org.junit.Before;
import org.junit.Ignore;
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
public class QuotedQuoteServiceTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @MockBean
    private QuoteOperationsFacade operations;

    private QuotedQuoteService quotedQuoteService;

    @Before
    public void setUp() throws Exception {
        quotedQuoteService = new QuotedQuoteService(operations);
    }

    @Test
    public void isResponsibleFor() {
        List<QuoteState> notResponsibleFor = new ArrayList<>(Arrays.asList(
                QuoteState.MODIFIED,
                QuoteState.REQUESTED,
                QuoteState.CANCELED,
                QuoteState.ACCEPTED,
                QuoteState.REJECTED,
                QuoteState.NEW,
                QuoteState.WAITING_RESPONSE,
                QuoteState.ASK
        ));

        notResponsibleFor.forEach(state -> assertThat(quotedQuoteService.isResponsibleFor(state)).isFalse());

        assertThat(quotedQuoteService.isResponsibleFor(QuoteState.QUOTED)).isTrue();

    }


    @Test
    public void cancel() {
        Quote quote = Mockito.mock(Quote.class);
        Quote savedQuote = Mockito.mock(Quote.class);
        quotedQuoteService.setQuote(quote);
        doNothing().when(operations).cancel(quote);
        when(operations.getQuote()).thenReturn(savedQuote);
        quotedQuoteService.cancel();
        Quote result = quotedQuoteService.getQuote();
        assertThat(result).isEqualTo(savedQuote);
    }

    @Test
    public void cancelWithNullThrowsAssertionError() {
        exception.expect(AssertionError.class);
        quotedQuoteService.setQuote(null);
        quotedQuoteService.cancel();
    }

    @Test
    public void modify() {
        Quote quote = Mockito.mock(Quote.class);
        Quote savedQuote = Mockito.mock(Quote.class);
        quotedQuoteService.setQuote(quote);

        doNothing().when(operations).modify(quote);
        when(operations.getQuote()).thenReturn(savedQuote);

        quotedQuoteService.modify();
        Quote result = quotedQuoteService.getQuote();
        assertThat(result).isEqualTo(savedQuote);
    }

    @Test
    public void modifyWithNullThrowsAssertionError() {
        exception.expect(AssertionError.class);
        quotedQuoteService.setQuote(null);
        quotedQuoteService.modify();
    }

    @Test
    public void modifyPrevQuoteShouldThrowException() throws QuoteVersionMismatchException {
        exception.expect(QuoteVersionMismatchException.class);
        Quote quote = Mockito.mock(Quote.class);
        quotedQuoteService.setQuote(quote);
        doThrow().when(operations).modify(quote);
//        when(operations.modify(quote)).thenThrow(QuoteVersionMismatchException.class);
        quotedQuoteService.modify();
    }

    @Test
    @Ignore
    public void send() {
    }


    @Test
    public void requestThrowsQuoteOperationNotAllowed() {
        exception.expect(QuoteOperationNotAllowedException.class);

        Quote quote = Mockito.mock(Quote.class);
        quotedQuoteService.setQuote(quote);
        quotedQuoteService.request();
    }

    @Test
    public void createThrowsQuoteOperationNotAllowed() {
        exception.expect(QuoteOperationNotAllowedException.class);

        Quote quote = Mockito.mock(Quote.class);
        quotedQuoteService.setQuote(quote);
        quotedQuoteService.create();
    }

    @Test
    public void rejectThrowsQuoteOperationNotAllowed() {
        exception.expect(QuoteOperationNotAllowedException.class);

        Quote quote = Mockito.mock(Quote.class);
        quotedQuoteService.setQuote(quote);
        quotedQuoteService.reject();
    }

    @Test
    public void acceptThrowsQuoteOperationNotAllowed() {
        exception.expect(QuoteOperationNotAllowedException.class);

        Quote quote = Mockito.mock(Quote.class);
        quotedQuoteService.setQuote(quote);
        quotedQuoteService.accept();
    }


    
}