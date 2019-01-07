package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.model.Quote.Quote;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class QuoteStateBasedServiceFactory {

    private List<QuoteStateBasedService> quoteStateBasedServices;

    @Autowired
    public QuoteStateBasedServiceFactory(List<QuoteStateBasedService> quoteStateBasedServices) {
        this.quoteStateBasedServices = quoteStateBasedServices;
    }

    public QuoteStateBasedService makeQuoteService(Quote quote) {
        return quoteStateBasedServices.stream().filter(quoteService -> quoteService.isResponsibleFor(quote.getStatus())).findFirst().orElseThrow();
    }
}
