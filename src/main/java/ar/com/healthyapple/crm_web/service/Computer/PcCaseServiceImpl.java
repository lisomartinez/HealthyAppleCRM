package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Computer.PcCase;
import ar.com.healthyapple.crm_web.repository.Computer.PcCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PcCaseServiceImpl implements PcCaseService {

    private PcCaseRepository pcCaseRepository;

    @Autowired
    public PcCaseServiceImpl(PcCaseRepository pcCaseRepository) {
        this.pcCaseRepository = pcCaseRepository;
    }

    @Override
    @Transactional
    public PcCase create(PcCase pcCase) throws AlreadyExistException {
         if (pcCaseRepository.findByPartNumberIgnoreCase(pcCase.getPartNumber()).isPresent()) {
             throw new AlreadyExistException("Pc Case already exists");
         } else {
            return pcCaseRepository.save(pcCase);
        }
    }

    @Override
    public PcCase read(Long id) throws NotFoundException {
       return pcCaseRepository.findById(id).orElseThrow(() -> new NotFoundException("Pc Case not found"));
    }

    @Override
    @Transactional
    public PcCase update(PcCase pcCase) throws NotFoundException {
        pcCaseRepository.findById(pcCase.getId()).orElseThrow(() -> new NotFoundException("Pc Case not found"));
        return pcCaseRepository.save(pcCase);
    }

    @Override
    @Transactional
    public void delete(PcCase pcCase) throws NotFoundException {
        pcCaseRepository.findById(pcCase.getId()).orElseThrow(() -> new NotFoundException("Pc Case not found"));
        pcCaseRepository.delete(pcCase);
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws NotFoundException {
        pcCaseRepository.findById(id).orElseThrow(() -> new NotFoundException("Pc Case not found"));
        pcCaseRepository.deleteById(id);
    }

    @Override
    public List<PcCase> findAll() {
        return pcCaseRepository.findAll();
    }

    @Override
    public PcCase findByBrandAndModel(String brand, String model) throws NotFoundException{
        return pcCaseRepository.findByBrandAndModelAllIgnoreCase(brand, model)
                .orElseThrow(() -> new NotFoundException("Pc Case not found"));
     }

    @Override
    public PcCase findByPartNumber(String partNumber) throws NotFoundException {
        return pcCaseRepository.findByPartNumberIgnoreCase(partNumber).orElseThrow(() -> new NotFoundException("Pc Case not found"));
    }
}
