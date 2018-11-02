package ar.com.healthyapple.crm_web.repository.Service;

import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Sale.ProductService;
import ar.com.healthyapple.crm_web.model.Sale.Sale;
import ar.com.healthyapple.crm_web.model.Sale.SaleState;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    public SaleBuilder setTag(String tag) {
        sale.setTag(tag);
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

    public SaleBuilder setClient(Client client) {
        sale.setClient(client);
        return this;
    }

    public SaleBuilder setOriginDate(LocalDate originDate) {
        sale.setOriginDate(originDate);
        return this;
    }

    public SaleBuilder setDueDate(LocalDate dueDate) {
        sale.setDueDate(dueDate);
        return this;
    }


    public SaleBuilder setFinalPrice(BigDecimal finalPrice) {
        sale.setPrice(finalPrice);
        return this;
    }

    public SaleBuilder setServiceList(List<ProductService> productServiceList) {
        sale.setProductServiceList(productServiceList);
        return this;
    }

    public Sale build() {
        return sale;
    }
}
