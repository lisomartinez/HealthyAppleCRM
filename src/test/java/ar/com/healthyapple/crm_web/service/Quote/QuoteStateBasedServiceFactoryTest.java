package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class QuoteStateBasedServiceFactoryTest {

    private QuoteStateBasedServiceFactory quoteFactory;

    @MockBean
    private AcceptedQuoteService acceptedQuoteService;

    @MockBean
    private ModifiedQuoteService modifiedQuoteService;

    @MockBean
    private NewQuoteService newQuoteStateBasedService;

    @MockBean
    private QuotedQuoteService quotedQuoteService;

    @MockBean
    private WaitingResponseQuoteService waitingResponseQuoteService;

    @MockBean
    private Quote quote;


    @Before
    public void setUp() throws Exception {
        List<QuoteStateBasedService> quoteStateBasedServices = new ArrayList<>(Arrays.asList(
                acceptedQuoteService,
                modifiedQuoteService,
                newQuoteStateBasedService,
                quotedQuoteService,
                waitingResponseQuoteService
        ));
        quoteFactory = new QuoteStateBasedServiceFactory(quoteStateBasedServices);
    }

    @Test
    public void makeQuoteService_new() {
        QuoteState newQuote = QuoteState.NEW;
        when(this.quote.getStatus()).thenReturn(newQuote);

        when(acceptedQuoteService.isResponsibleFor(newQuote)).thenReturn(Boolean.FALSE);
        when(modifiedQuoteService.isResponsibleFor(newQuote)).thenReturn(Boolean.FALSE);
        when(quotedQuoteService.isResponsibleFor(newQuote)).thenReturn(Boolean.FALSE);
        when(waitingResponseQuoteService.isResponsibleFor(newQuote)).thenReturn(Boolean.FALSE);

        when(newQuoteStateBasedService.isResponsibleFor(newQuote)).thenReturn(Boolean.TRUE);
        doNothing().when(newQuoteStateBasedService).setQuote(this.quote);

        QuoteStateBasedService result = quoteFactory.makeQuoteService(quote);

        assertThat(result).isEqualTo(newQuoteStateBasedService);

    }
}