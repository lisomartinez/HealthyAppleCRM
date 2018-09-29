package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Component;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface TechnicalSpecificationItemService {
    Component create(Component technicalSpecification) throws AlreadyExistException;

    Component read(Long id) throws NotFoundException;

    Component update(Component technicalSpecification) throws NotFoundException;

    void deleteById(Long id) throws NotFoundException;

    void delete(Component technicalSpecification)throws NotFoundException;

    List<Component> findAll();
}
