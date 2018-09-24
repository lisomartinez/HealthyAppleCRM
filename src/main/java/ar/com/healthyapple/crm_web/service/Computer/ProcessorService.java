package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Computer.Processor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProcessorService {

    Processor create(Processor processor) throws AlreadyExistException;
    Processor read(Long id) throws NotFoundException;
    Processor update(Processor processor) throws NotFoundException;
    void deleteById(Long id) throws NotFoundException;
    void delete(Processor processor) throws NotFoundException;
    List<Processor> findAll();
    Processor findByBrandAndModel(String brand, String model) throws NotFoundException;
    Processor findByPartNumber(String partNumber) throws NotFoundException;
}
