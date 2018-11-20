package ar.com.healthyapple.crm_web.service.Sale;

import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;
import ar.com.healthyapple.crm_web.service.Quote.*;
import org.hibernate.cfg.NotYetImplementedException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)

public class QuoteStateBasedProductServiceFactoryTest {


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @MockBean
    private NewQuoteService newQuoteStateBasedService;

    @MockBean
    private AcceptedQuoteService acceptedQuoteService;

    @MockBean
    private RequestedQuoteService requestedQuoteService;

    @MockBean
    private RejectedQuoteService rejectedQuoteService;

    @MockBean
    private QuotedQuoteService quotedQuoteService;

    @MockBean
    private ModifiedQuoteService modifiedQuoteService;

    @MockBean
    private Quote quote;

    private List<QuoteStateBasedService> quoteStateBasedServiceList;

    private QuoteStateBasedServiceFactory quoteStateBasedServiceFactory;


    @Before
    public void setUp() {
        quoteStateBasedServiceList = Arrays.asList(newQuoteStateBasedService, acceptedQuoteService, rejectedQuoteService, requestedQuoteService, modifiedQuoteService, quotedQuoteService);
        quoteStateBasedServiceFactory = new QuoteStateBasedServiceFactory(quoteStateBasedServiceList);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void makeNewQuoteService() {
        when(quote.getStatus()).thenReturn(QuoteState.NEW);
        when(newQuoteStateBasedService.isResponsibleFor(quote.getStatus())).thenReturn(true);
        assertThat(quoteStateBasedServiceFactory.makeQuoteService(quote)).isInstanceOf(NewQuoteService.class);
    }

    @Test
    public void makeRequestedQuoteService() {
        when(quote.getStatus()).thenReturn(QuoteState.REQUESTED);
        when(requestedQuoteService.isResponsibleFor(quote.getStatus())).thenReturn(true);
        assertThat(quoteStateBasedServiceFactory.makeQuoteService(quote)).isInstanceOf(RequestedQuoteService.class);
    }

    @Test
    public void makeQuotedQuoteService() {
        when(quote.getStatus()).thenReturn(QuoteState.QUOTED);
        when(quotedQuoteService.isResponsibleFor(quote.getStatus())).thenReturn(true);
        assertThat(quoteStateBasedServiceFactory.makeQuoteService(quote)).isInstanceOf(QuotedQuoteService.class);
    }

    @Test
    public void makeRejectedQuoteService() {
        when(quote.getStatus()).thenReturn(QuoteState.REJECTED);
        when(rejectedQuoteService.isResponsibleFor(quote.getStatus())).thenReturn(true);
        assertThat(quoteStateBasedServiceFactory.makeQuoteService(quote)).isInstanceOf(RejectedQuoteService.class);
    }

    @Test
    public void makeModifiedQuoteService() {
        when(quote.getStatus()).thenReturn(QuoteState.MODIFIED);
        when(modifiedQuoteService.isResponsibleFor(quote.getStatus())).thenReturn(true);
        assertThat(quoteStateBasedServiceFactory.makeQuoteService(quote)).isInstanceOf(ModifiedQuoteService.class);
    }

    @Test
    public void makeAcceptedQuoteService() {
        when(quote.getStatus()).thenReturn(QuoteState.ACCEPTED);
        when(acceptedQuoteService.isResponsibleFor(quote.getStatus())).thenReturn(true);
        assertThat(quoteStateBasedServiceFactory.makeQuoteService(quote)).isInstanceOf(AcceptedQuoteService.class);
    }

    @Test

    public void makeNotYetImplementedQuoteService() {
        thrown.expect(NotYetImplementedException.class);
        when(quote.getStatus()).thenReturn(null);
        assertThat(quoteStateBasedServiceFactory.makeQuoteService(quote)).isInstanceOf(AcceptedQuoteService.class);
    }

}