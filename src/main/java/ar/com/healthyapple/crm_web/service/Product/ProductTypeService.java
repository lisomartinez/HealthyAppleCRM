package ar.com.healthyapple.crm_web.service.Product;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.exceptions.PageDoesNotExistException;
import ar.com.healthyapple.crm_web.model.Product.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductTypeService {

    ProductType create(ProductType productType) throws AlreadyExistException;

    ProductType read(Long id) throws NotFoundException;

    ProductType update(ProductType productType) throws NotFoundException;

    void deleteById(Long id) throws NotFoundException;

    void delete(ProductType productType) throws NotFoundException;

    Page<ProductType> findAll(Pageable pageable) throws PageDoesNotExistException;
}
