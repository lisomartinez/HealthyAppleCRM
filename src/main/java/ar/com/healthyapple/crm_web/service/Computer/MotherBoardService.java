package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.dto.Computer.MotherBoardDto;
import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Computer.MotherBoard;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MotherBoardService {

    MotherBoard create(MotherBoard motherBoard) throws AlreadyExistException;
    MotherBoard read(Long id) throws NotFoundException;
    MotherBoard update(MotherBoard motherBoard) throws NotFoundException;
    void delete(MotherBoard motherBoard) throws  NotFoundException;
    void deleteById(Long id) throws NotFoundException;
    List<MotherBoard> findAll();
    MotherBoard findByBrandAndModel(@NonNull String brand, @NonNull String model) throws NotFoundException;
    MotherBoard findByPartNumber(@NonNull String partNumber) throws NotFoundException;
}
