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
public class ModifiedQuoteServiceTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @MockBean
    private QuoteOperationsFacade operations;

    private ModifiedQuoteService modifiedQuoteService;

    @Before

    public void setUp() throws Exception {
        modifiedQuoteService = new ModifiedQuoteService(operations);
    }

    @Test
    public void isResponsibleFor() {
        List<QuoteState> notResponsibleFor = new ArrayList<>(Arrays.asList(
                QuoteState.ACCEPTED,
                QuoteState.REQUESTED,
                QuoteState.CANCELED,
                QuoteState.QUOTED,
                QuoteState.REJECTED,
                QuoteState.NEW,
                QuoteState.WAITING_RESPONSE,
                QuoteState.ASK
        ));

        notResponsibleFor.forEach(state -> assertThat(modifiedQuoteService.isResponsibleFor(state)).isFalse());

        assertThat(modifiedQuoteService.isResponsibleFor(QuoteState.MODIFIED)).isTrue();

    }

    @Test
    public void cancel() {
        Quote quote = Mockito.mock(Quote.class);
        Quote savedQuote = Mockito.mock(Quote.class);
        modifiedQuoteService.setQuote(quote);
        doNothing().when(operations).cancel(quote);
        when(operations.getQuote()).thenReturn(savedQuote);
        modifiedQuoteService.cancel();
        Quote result = modifiedQuoteService.getQuote();
        assertThat(result).isEqualTo(savedQuote);
    }

    @Test
    public void cancelWithNullThrowsAssertionError() {
        exception.expect(AssertionError.class);
        modifiedQuoteService.setQuote(null);
        modifiedQuoteService.cancel();
    }

    @Test
    public void modify() {
        Quote quote = Mockito.mock(Quote.class);
        Quote savedQuote = Mockito.mock(Quote.class);
        modifiedQuoteService.setQuote(quote);
        doNothing().when(operations).modify(quote);
        when(operations.getQuote()).thenReturn(savedQuote);
        modifiedQuoteService.modify();
        Quote result = modifiedQuoteService.getQuote();
        assertThat(result).isEqualTo(savedQuote);
    }

    @Test
    public void modifyWithNullThrowsAssertionError() {
        exception.expect(AssertionError.class);
        modifiedQuoteService.setQuote(null);
        modifiedQuoteService.modify();
    }

    @Test
    public void modifyPrevQuoteShouldThrowException() throws QuoteVersionMismatchException {
        exception.expect(QuoteVersionMismatchException.class);
        Quote quote = Mockito.mock(Quote.class);
        modifiedQuoteService.setQuote(quote);
        doThrow(QuoteVersionMismatchException.class).when(operations).modify(quote);
        modifiedQuoteService.modify();
    }

    @Test
    @Ignore
    public void send() {
    }

    @Test
    public void requestThrowsQuoteOperationNotAllowed() {
        exception.expect(QuoteOperationNotAllowedException.class);

        Quote quote = Mockito.mock(Quote.class);
        modifiedQuoteService.setQuote(quote);
        modifiedQuoteService.request();
    }

    @Test
    public void createThrowsQuoteOperationNotAllowed() {
        exception.expect(QuoteOperationNotAllowedException.class);

        Quote quote = Mockito.mock(Quote.class);
        modifiedQuoteService.setQuote(quote);
        modifiedQuoteService.create();
    }

    @Test
    public void rejectThrowsQuoteOperationNotAllowed() {
        exception.expect(QuoteOperationNotAllowedException.class);

        Quote quote = Mockito.mock(Quote.class);
        modifiedQuoteService.setQuote(quote);
        modifiedQuoteService.reject();
    }

    @Test
    public void acceptThrowsQuoteOperationNotAllowed() {
        exception.expect(QuoteOperationNotAllowedException.class);

        Quote quote = Mockito.mock(Quote.class);
        modifiedQuoteService.setQuote(quote);
        modifiedQuoteService.accept();
    }

}