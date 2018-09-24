package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Computer.HardDrive;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HardDiskService {

    HardDrive create(HardDrive hardDrive) throws AlreadyExistException;

    HardDrive readById(Long id) throws  NotFoundException;

    HardDrive update(HardDrive hardDrive) throws  NotFoundException;

    void deleteById(Long id) throws NotFoundException;

    void delete(HardDrive hardDrive) throws NotFoundException;

    List<HardDrive> findAll();

    HardDrive findHardDriveByBrandAndModel (@NonNull String brand, @NonNull String model) throws NotFoundException;

    HardDrive findByPartNumber(@NonNull String partNumber) throws NotFoundException;
}
