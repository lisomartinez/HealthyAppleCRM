package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.TechnicalSpecificationItem;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface TechnicalSpecificationItemService {
    TechnicalSpecificationItem create(TechnicalSpecificationItem technicalSpecification) throws AlreadyExistException;

    TechnicalSpecificationItem read(Long id) throws NotFoundException;

    TechnicalSpecificationItem update(TechnicalSpecificationItem technicalSpecification) throws NotFoundException;

    void deleteById(Long id) throws NotFoundException;

    void delete(TechnicalSpecificationItem technicalSpecification)throws NotFoundException;

    List<TechnicalSpecificationItem> findAll();
}
