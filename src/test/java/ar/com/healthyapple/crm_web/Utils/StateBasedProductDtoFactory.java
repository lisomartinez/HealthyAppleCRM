package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.dto.Product.ProductDto;
import ar.com.healthyapple.crm_web.dto.Product.StateBasedProductDto;
import ar.com.healthyapple.crm_web.model.Product.ProductState;

public class StateBasedProductDtoFactory {
    public static StateBasedProductDto makeProductDto() {
        StateBasedProductDto product = new StateBasedProductDto();
        product.setId(1L);
        product.setState(ProductState.ACTUAL);
        product.setProduct(ProductDtoFactory.makeProductDto());
        return product;
    }

}
