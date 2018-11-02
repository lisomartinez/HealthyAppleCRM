package ar.com.healthyapple.crm_web.service.Sale;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Sale.ProductService;

@org.springframework.stereotype.Service
public interface ServiceService {
    ProductService create(ProductService productService) throws AlreadyExistException;
    ProductService read(Long id) throws NotFoundException;
    ProductService update(ProductService productService) throws NotFoundException;
    void deleteById(Long id) throws NotFoundException;
    void delete(ProductService productService) throws NotFoundException;
}
