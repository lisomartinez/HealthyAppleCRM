package ar.com.healthyapple.crm_web.service.Product;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.model.Product.StateBasedProduct;

public interface StateBasedProductService {
    StateBasedProduct create(StateBasedProduct stateBasedProduct) throws AlreadyExistException;
}
