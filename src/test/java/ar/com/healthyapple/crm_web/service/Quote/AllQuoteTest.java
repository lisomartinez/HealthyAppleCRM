package ar.com.healthyapple.crm_web.service.Quote;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    AcceptedQuoteServiceTest.class,
    ModifiedQuoteServiceTest.class,
    NewQuoteServiceTest.class,
    QuotedQuoteServiceTest.class,
    QuoteListServiceTest.class,
    QuoteOperationsFacadeTest.class,
    QuoteServiceTest.class,
    QuoteStateBasedServiceFactoryTest.class,
    RequestedQuoteServiceTest.class,
    WaitingResponseQuoteServiceTest.class
})
public class AllQuoteTest {
}
