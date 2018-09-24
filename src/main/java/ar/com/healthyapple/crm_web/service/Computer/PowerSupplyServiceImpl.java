package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Computer.PowerSupply;
import ar.com.healthyapple.crm_web.repository.Computer.PowerSupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PowerSupplyServiceImpl implements PowerSupplyService {


    private PowerSupplyRepository powerSupplyRepository;

    @Autowired
    public PowerSupplyServiceImpl(PowerSupplyRepository powerSupplyRepository) {
        this.powerSupplyRepository = powerSupplyRepository;
    }

    @Override
    @Transactional
    public PowerSupply create(PowerSupply powerSupply) throws AlreadyExistException {
        if (powerSupplyRepository.findByPartNumberIgnoreCase(powerSupply.getPartNumber()).isPresent()) {
            throw new AlreadyExistException("Power Supply already exists");
        } else {

            return powerSupplyRepository.save(powerSupply);
        }
    }


    @Override
    public PowerSupply read(Long id) throws NotFoundException {
        return powerSupplyRepository.findById(id).orElseThrow(() -> new NotFoundException("Power Supply not found"));
    }

    @Override
    @Transactional
    public PowerSupply update(PowerSupply powerSupply) throws NotFoundException {
        powerSupplyRepository.findById(powerSupply.getId()).orElseThrow(() -> new NotFoundException("Power Supply not found"));
        return powerSupplyRepository.save(powerSupply);
    }

    @Override
    @Transactional
    public void delete(PowerSupply powerSupply) throws NotFoundException {
        powerSupplyRepository.findById(powerSupply.getId()).orElseThrow(() -> new NotFoundException("Power Supply not found"));
        powerSupplyRepository.delete(powerSupply);
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws NotFoundException {
        powerSupplyRepository.findById(id).orElseThrow(() -> new NotFoundException("Power Supply not found"));
        powerSupplyRepository.deleteById(id);
    }

    @Override
    public List<PowerSupply> findAll() {
        return powerSupplyRepository.findAll();
    }

    @Override
    public PowerSupply findByBrandAndModel(String brand, String model) throws NotFoundException{
              return  powerSupplyRepository.findByBrandAndModelAllIgnoreCase(brand, model)
                     .orElseThrow(() -> new NotFoundException("Power Supply not found"));
    }

    @Override
    public PowerSupply findByPartNumber(String partNumber) throws NotFoundException {
        return powerSupplyRepository.findByPartNumberIgnoreCase(partNumber)
                .orElseThrow(() -> new NotFoundException("Power Supply not found"));
    }
}
