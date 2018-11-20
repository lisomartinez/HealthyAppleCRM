package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.model.Product.Product;
import ar.com.healthyapple.crm_web.model.Product.ProductState;
import ar.com.healthyapple.crm_web.model.Product.StateBasedProduct;

public class StateBasedProductFactory {
    public static StateBasedProduct makeProduct() {
        StateBasedProduct product = new StateBasedProduct();
        product.setId(1L);
        product.setState(ProductState.ACTUAL);
        product.setProduct(ProductFactory.makeProduct());
        return product;
    }
}
