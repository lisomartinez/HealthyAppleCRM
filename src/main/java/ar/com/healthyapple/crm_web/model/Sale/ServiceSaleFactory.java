package ar.com.healthyapple.crm_web.model.Sale;

;

public class ServiceSaleFactory implements SaleFactory {
    @Override
    public Sale createSale() {
        return new Sale();
    }
}
