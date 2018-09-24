package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Computer.PcCase;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PcCaseService {

    PcCase create(PcCase pcCase) throws AlreadyExistException;

    PcCase read(Long id) throws NotFoundException;

    PcCase update(PcCase pcCase) throws NotFoundException;

    void delete(PcCase pcCase) throws  NotFoundException;

    void deleteById(Long id) throws NotFoundException;

    List<PcCase> findAll();

    PcCase findByBrandAndModel(@NonNull String brand, @NonNull String model) throws NotFoundException;

    PcCase findByPartNumber(@NonNull String partNumber) throws NotFoundException;
}
