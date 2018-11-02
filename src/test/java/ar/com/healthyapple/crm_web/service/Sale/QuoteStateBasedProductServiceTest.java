package ar.com.healthyapple.crm_web.service.Sale;

import ar.com.healthyapple.crm_web.service.Quote.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class QuoteStateBasedProductServiceTest {

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

    private List<QuoteStateBasedService> quoteStateBasedServiceList;


    @BeforeEach
    public void setUp() {
    }

    @Test
    public void isResponsibleFor() {
    }

    @Test
    public void setQuote() {
    }

    @Test
    public void request() {
    }

    @Test
    public void create() {
    }
}