package ar.com.healthyapple.crm_web.service.Product;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Product.Component;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ComponentService {

    Component create(Component component) throws AlreadyExistException;

    Component read(Long id) throws NotFoundException;

    Component update(Component component) throws NotFoundException;

    void deleteById(Long id) throws NotFoundException;

    void delete(Component component) throws NotFoundException;

    Page<Component> findAll(Pageable pageable) throws NotFoundException;


}
