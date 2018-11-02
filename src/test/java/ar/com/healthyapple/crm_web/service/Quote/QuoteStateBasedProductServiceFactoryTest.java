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
public class QuoteStateBasedProductServiceFactoryTest {

    private QuoteStateBasedServiceFactory quoteFactory;

    @MockBean
    private AcceptedQuoteStateBasedService acceptedQuoteStateBasedService;

    @MockBean
    private ModifiedQuoteStateBasedService modifiedQuoteStateBasedService;

    @MockBean
    private NewQuoteStateBasedService newQuoteStateBasedService;

    @MockBean
    private QuotedQuoteStateBasedService quotedQuoteStateBasedService;

    @MockBean
    private RejectedQuoteStatedBasedService rejectedQuoteStatedBasedService;

    @MockBean
    private WaitingResponseQuoteStateBasedService waitingResponseQuoteStateBasedService;

    @MockBean
    private Quote quote;


    @Before
    public void setUp() throws Exception {
        List<QuoteStateBasedService> quoteStateBasedServices = new ArrayList<>(Arrays.asList(
                acceptedQuoteStateBasedService,
                modifiedQuoteStateBasedService,
                newQuoteStateBasedService,
                quotedQuoteStateBasedService,
                rejectedQuoteStatedBasedService,
                waitingResponseQuoteStateBasedService
        ));
        quoteFactory = new QuoteStateBasedServiceFactory(quoteStateBasedServices);
    }

    @Test
    public void makeQuoteService_new() {
        QuoteState newQuote = QuoteState.NEW;
        when(this.quote.getStatus()).thenReturn(newQuote);

        when(acceptedQuoteStateBasedService.isResponsibleFor(newQuote)).thenReturn(Boolean.FALSE);
        when(modifiedQuoteStateBasedService.isResponsibleFor(newQuote)).thenReturn(Boolean.FALSE);
        when(quotedQuoteStateBasedService.isResponsibleFor(newQuote)).thenReturn(Boolean.FALSE);
        when(rejectedQuoteStatedBasedService.isResponsibleFor(newQuote)).thenReturn(Boolean.FALSE);
        when(waitingResponseQuoteStateBasedService.isResponsibleFor(newQuote)).thenReturn(Boolean.FALSE);

        when(newQuoteStateBasedService.isResponsibleFor(newQuote)).thenReturn(Boolean.TRUE);
        doNothing().when(newQuoteStateBasedService).setQuote(this.quote);

        QuoteStateBasedService result = quoteFactory.makeQuoteService(quote);

        assertThat(result).isEqualTo(newQuoteStateBasedService);

    }
}