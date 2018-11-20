package ar.com.healthyapple.crm_web.repository.Sale;

import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Product.Product;
import ar.com.healthyapple.crm_web.model.Product.ProductState;
import ar.com.healthyapple.crm_web.model.Product.StateBasedProduct;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteItem;
import ar.com.healthyapple.crm_web.model.Sale.Sale;
import ar.com.healthyapple.crm_web.model.Sale.SaleItem;
import ar.com.healthyapple.crm_web.model.Sale.SaleState;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class SaleBuilder {

    private Sale sale;

    public SaleBuilder() {
        this.sale = new Sale();
    }

    public SaleBuilder setId(Long id) {
        sale.setId(id);
        return this;
    }

    public SaleBuilder setDescription(String description) {
        sale.setDescription(description);
        return this;
    }

    public SaleBuilder setState(SaleState state) {
        sale.setState(state);
        return this;
    }


    public SaleBuilder setOriginDate(LocalDate originDate) {
        sale.setStartDate(originDate);
        return this;
    }


    public SaleBuilder setQuote(Quote quote) {
        sale.setQuote(quote);
        return this;
    }
    public Sale build() {
        return sale;
    }
}
