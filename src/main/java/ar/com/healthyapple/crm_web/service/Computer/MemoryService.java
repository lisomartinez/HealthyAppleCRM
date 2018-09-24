package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.BadRequestException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Computer.HardDrive;
import ar.com.healthyapple.crm_web.model.Computer.Memory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MemoryService {
    Memory create(Memory memory) throws AlreadyExistException;
    List<Memory> findAll();
    void deleteById(Long id) throws NotFoundException;
    void delete(Memory memory) throws NotFoundException;
    Memory read(Long id) throws NotFoundException;
    Memory update(Memory memory) throws NotFoundException;
    Memory findMemoryByBrandAndModel(@NonNull String brand, @NonNull String model) throws NotFoundException;
    Memory findByPartNumber(@NonNull String partNumber) throws NotFoundException;
}
