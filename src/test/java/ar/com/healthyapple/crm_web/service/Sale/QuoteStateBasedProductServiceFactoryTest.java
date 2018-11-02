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
    private NewQuoteStateBasedService newQuoteStateBasedService;

    @MockBean
    private AcceptedQuoteStateBasedService acceptedQuoteStateBasedService;

    @MockBean
    private RequestedQuoteStateBaseService requestedQuoteStateBaseService;

    @MockBean
    private RejectedQuoteStatedBasedService rejectedQuoteStatedBasedService;

    @MockBean
    private QuotedQuoteStateBasedService quotedQuoteStateBasedService;

    @MockBean
    private ModifiedQuoteStateBasedService modifiedQuoteStateBasedService;

    @MockBean
    private Quote quote;

    private List<QuoteStateBasedService> quoteStateBasedServiceList;

    private QuoteStateBasedServiceFactory quoteStateBasedServiceFactory;


    @Before
    public void setUp() {
        quoteStateBasedServiceList = Arrays.asList(newQuoteStateBasedService, acceptedQuoteStateBasedService, rejectedQuoteStatedBasedService, requestedQuoteStateBaseService, modifiedQuoteStateBasedService, quotedQuoteStateBasedService);
        quoteStateBasedServiceFactory = new QuoteStateBasedServiceFactory(quoteStateBasedServiceList);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void makeNewQuoteService() {
        when(quote.getStatus()).thenReturn(QuoteState.NEW);
        when(newQuoteStateBasedService.isResponsibleFor(quote.getStatus())).thenReturn(true);
        assertThat(quoteStateBasedServiceFactory.makeQuoteService(quote)).isInstanceOf(NewQuoteStateBasedService.class);
    }

    @Test
    public void makeRequestedQuoteService() {
        when(quote.getStatus()).thenReturn(QuoteState.REQUESTED);
        when(requestedQuoteStateBaseService.isResponsibleFor(quote.getStatus())).thenReturn(true);
        assertThat(quoteStateBasedServiceFactory.makeQuoteService(quote)).isInstanceOf(RequestedQuoteStateBaseService.class);
    }

    @Test
    public void makeQuotedQuoteService() {
        when(quote.getStatus()).thenReturn(QuoteState.QUOTED);
        when(quotedQuoteStateBasedService.isResponsibleFor(quote.getStatus())).thenReturn(true);
        assertThat(quoteStateBasedServiceFactory.makeQuoteService(quote)).isInstanceOf(QuotedQuoteStateBasedService.class);
    }

    @Test
    public void makeRejectedQuoteService() {
        when(quote.getStatus()).thenReturn(QuoteState.REJECTED);
        when(rejectedQuoteStatedBasedService.isResponsibleFor(quote.getStatus())).thenReturn(true);
        assertThat(quoteStateBasedServiceFactory.makeQuoteService(quote)).isInstanceOf(RejectedQuoteStatedBasedService.class);
    }

    @Test
    public void makeModifiedQuoteService() {
        when(quote.getStatus()).thenReturn(QuoteState.MODIFIED);
        when(modifiedQuoteStateBasedService.isResponsibleFor(quote.getStatus())).thenReturn(true);
        assertThat(quoteStateBasedServiceFactory.makeQuoteService(quote)).isInstanceOf(ModifiedQuoteStateBasedService.class);
    }

    @Test
    public void makeAcceptedQuoteService() {
        when(quote.getStatus()).thenReturn(QuoteState.ACCEPTED);
        when(acceptedQuoteStateBasedService.isResponsibleFor(quote.getStatus())).thenReturn(true);
        assertThat(quoteStateBasedServiceFactory.makeQuoteService(quote)).isInstanceOf(AcceptedQuoteStateBasedService.class);
    }

    @Test

    public void makeNotYetImplementedQuoteService() {
        thrown.expect(NotYetImplementedException.class);
        when(quote.getStatus()).thenReturn(null);
        assertThat(quoteStateBasedServiceFactory.makeQuoteService(quote)).isInstanceOf(AcceptedQuoteStateBasedService.class);
    }

}