package ar.com.healthyapple.crm_web.service.Product;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.exceptions.PageDoesNotExistException;
import ar.com.healthyapple.crm_web.model.Product.Component;
import ar.com.healthyapple.crm_web.model.Product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Product create(Product product) throws AlreadyExistException;

    Product read(Long id) throws NotFoundException;

    Product update(Product product) throws NotFoundException;

    void deleteById(Long id) throws NotFoundException;

    void delete(Product product) throws NotFoundException;

    Product updateDescription(Long id, String description) throws NotFoundException;

    Product updateSpecs(Long id, List<Component> specs) throws NotFoundException;

    Page<Product> findAll(Pageable pageable) throws PageDoesNotExistException;


}
