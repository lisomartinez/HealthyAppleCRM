package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Computer.HardDrive;
import ar.com.healthyapple.crm_web.repository.Computer.HardDriveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HardDiskServiceImpl implements HardDiskService {

    private HardDriveRepository hardDriveRepository;

    @Autowired
    public HardDiskServiceImpl(HardDriveRepository hardDriveRepository) {
        this.hardDriveRepository = hardDriveRepository;
    }

    @Override
    @Transactional
    public HardDrive create(HardDrive hardDrive) throws AlreadyExistException {
        if (hardDriveRepository.findByPartNumberIgnoreCase(hardDrive.getPartNumber()).isPresent()) {
            throw new AlreadyExistException("Hard Drive already Exists");
        } else {
            return hardDriveRepository.save(hardDrive);
        }
    }

    @Override
    public HardDrive readById(Long id) throws NotFoundException{
        HardDrive hardDrive = hardDriveRepository.findById(id).orElseThrow(() -> new NotFoundException("Hard drive does not exist"));
       return hardDrive;
    }

    @Override
    public List<HardDrive> findAll() {
        return hardDriveRepository.findAll();
    }

    //TODO: PROBAR
    @Override
    @Transactional
    public void deleteById(Long id) throws  NotFoundException{
        hardDriveRepository.findById(id).orElseThrow(() -> new NotFoundException("Hard drive does not exist"));
        hardDriveRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HardDrive update(HardDrive hardDrive) throws NotFoundException {
       hardDriveRepository.findById(hardDrive.getId())
               .orElseThrow(() -> new NotFoundException("Hard drive does not exist"));
       return hardDriveRepository.save(hardDrive);
    }

    @Override
    @Transactional
    public void delete(HardDrive hardDrive) throws  NotFoundException {
        hardDriveRepository.findById(hardDrive.getId())
                .orElseThrow(() -> new NotFoundException("Hard drive does not exist"));
        hardDriveRepository.delete(hardDrive);
    }

    @Override
    public HardDrive findHardDriveByBrandAndModel(@NonNull String brand, @NonNull String model) throws NotFoundException {
        return hardDriveRepository.findHardDriveByBrandAndModelAllIgnoreCase(brand, model)
                .orElseThrow(() -> new NotFoundException("Hard drive does not exist"));
    }

    @Override
    public HardDrive findByPartNumber(String partNumber) throws NotFoundException {
        return hardDriveRepository.findByPartNumberIgnoreCase(partNumber).orElseThrow(() -> new NotFoundException("Hard drive does not exist"));
    }
}
