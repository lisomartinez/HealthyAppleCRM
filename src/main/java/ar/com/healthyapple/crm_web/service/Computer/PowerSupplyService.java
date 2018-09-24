package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Computer.PowerSupply;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PowerSupplyService {

    PowerSupply create(PowerSupply powerSupply) throws AlreadyExistException;

    PowerSupply read(Long id) throws NotFoundException;

    PowerSupply update(PowerSupply powerSupply) throws NotFoundException;

    void delete(PowerSupply powerSupply) throws NotFoundException;

    void deleteById(Long id) throws NotFoundException;

    List<PowerSupply> findAll();

    PowerSupply findByBrandAndModel(@NonNull String brand,@NonNull  String model) throws NotFoundException;

    PowerSupply findByPartNumber(@NonNull String partNumber) throws NotFoundException;
}
