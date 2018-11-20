package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.model.Product.Product;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductFactory {
    public static Product makeProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setProductType(ProductTypeFactory.makeProductType());
        product.setDescription("description");
        product.setComponents(new ArrayList<>(Arrays.asList(ComponentFactory.makeComponent())));
        return product;
    }

    public static Product makeEmptyProduct() {
        return new Product();
    }
}
