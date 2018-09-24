package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.TechnicalSpecification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TechnicalSpecificationService {

    TechnicalSpecification create(TechnicalSpecification technicalSpecification) throws AlreadyExistException;

    TechnicalSpecification read(Long id) throws NotFoundException;

    TechnicalSpecification update(TechnicalSpecification technicalSpecification) throws NotFoundException;

    void deleteById(Long id) throws NotFoundException;

    void delete(TechnicalSpecification technicalSpecification)throws NotFoundException;

    List<TechnicalSpecification> findAll();
}
