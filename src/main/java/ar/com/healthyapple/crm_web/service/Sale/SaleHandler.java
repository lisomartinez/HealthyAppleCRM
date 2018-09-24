package ar.com.healthyapple.crm_web.service.Sale;

import ar.com.healthyapple.crm_web.model.Product;
import ar.com.healthyapple.crm_web.model.Sale.Sale;
import ar.com.healthyapple.crm_web.model.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class SaleHandler {

    private Sale sale;

    public SaleHandler() {
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    BigDecimal getFinalPrice() {
        return sale.getFinalPrice();
    }

    List<Product> getProducts() {
        return sale.getServiceList().stream()
                .map(Service::getProduct)
                .collect(Collectors.toList());
    }

    List<Service> getServices() {
        return sale.getServiceList();
    }

    List<Service> getServicesByProduct(Product product) {
        return sale.getServiceList().stream()
                .filter(service -> service.getProduct().equals(product))
                .collect(Collectors.toList());
    }

    void addService(Service service) {
        sale.getServiceList().add(service);
    }

    BigDecimal getTotalCost() {
        return sale.getTotalCost();
    }

    void setCost(Service service, BigDecimal cost) {
        sale.getServiceList().stream()
                .filter(serviceItem -> serviceItem.getId().equals(service.getId()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
