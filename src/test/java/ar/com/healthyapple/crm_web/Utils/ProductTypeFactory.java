package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.model.Product.ProductType;

public class ProductTypeFactory {

    public static ProductType makeProductType() {
        ProductType productType = new ProductType();
        productType.setId(1L);
        productType.setName("name");
        return productType;
    }

    public static ProductType makeEmptyProductType() {
        return new ProductType();
    }
}
