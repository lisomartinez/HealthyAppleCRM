package ar.com.healthyapple.crm_web.service.Product;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.exceptions.PageDoesNotExistException;
import ar.com.healthyapple.crm_web.model.Product.ComponentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ComponentTypeService {

    ComponentType create(ComponentType componentType) throws AlreadyExistException;

    ComponentType read(Long id) throws NotFoundException;

    ComponentType update(ComponentType componentType) throws NotFoundException;

    void deleteById(Long id) throws NotFoundException;

    void delete(ComponentType componentType) throws NotFoundException;

    Page<ComponentType> findAll(Pageable pageable) throws PageDoesNotExistException;
}
