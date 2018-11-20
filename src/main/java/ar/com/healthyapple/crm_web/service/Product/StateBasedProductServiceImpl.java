package ar.com.healthyapple.crm_web.service.Product;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.model.Product.StateBasedProduct;
import ar.com.healthyapple.crm_web.repository.Product.StateBasedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StateBasedProductServiceImpl implements StateBasedProductService {

    private StateBasedProductRepository stateBasedProductRepository;


    @Autowired
    public StateBasedProductServiceImpl(StateBasedProductRepository stateBasedProductRepository) {
        this.stateBasedProductRepository = stateBasedProductRepository;
    }

    @Override
    public StateBasedProduct create(StateBasedProduct stateBasedProduct) throws AlreadyExistException {
        return stateBasedProductRepository.save(stateBasedProduct);
    }
}
