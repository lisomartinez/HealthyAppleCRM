package ar.com.healthyapple.crm_web.service.Sale;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Service;

@org.springframework.stereotype.Service
public interface ServiceService {
    Service create(Service service) throws AlreadyExistException;
    Service read(Long id) throws NotFoundException;
    Service update(Service service) throws NotFoundException;
    void deleteById(Long id) throws NotFoundException;
    void delete(Service service) throws NotFoundException;
}
