package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.model.Sale.Sale;
import ar.com.healthyapple.crm_web.model.Sale.SaleState;

import java.time.LocalDate;

public class SaleFactory {
    public static Sale makeSale() {
        Sale sale = new Sale();
        sale.setId(1L);
        sale.setDescription("description");
        sale.setState(SaleState.IN_PROGRESS);
        sale.setStartDate(LocalDate.of(2010, 9, 11));

        sale.setQuote(QuoteFactory.makeQuote());
        return sale;
    }
}
