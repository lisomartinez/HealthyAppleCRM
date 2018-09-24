package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.TechnicalSpecification;
import ar.com.healthyapple.crm_web.repository.TechnicalSpecificationRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicalSpecificationServiceImpl implements TechnicalSpecificationService {

    private TechnicalSpecificationRespository technicalSpecificationRespository;

    @Autowired
    public TechnicalSpecificationServiceImpl(TechnicalSpecificationRespository technicalSpecificationRespository) {
        this.technicalSpecificationRespository = technicalSpecificationRespository;
    }

    @Override
    public TechnicalSpecification create(TechnicalSpecification technicalSpecification) throws AlreadyExistException {
            return technicalSpecificationRespository.save(technicalSpecification);
    }

    @Override
    public TechnicalSpecification read(Long id) throws NotFoundException {
        return technicalSpecificationRespository.findById(id)
                .orElseThrow(() -> new NotFoundException("Technical Specification does not exists"));
    }

    @Override
    public TechnicalSpecification update(TechnicalSpecification technicalSpecification) throws NotFoundException {
        technicalSpecificationRespository.findById(technicalSpecification.getId())
                .orElseThrow(() -> new NotFoundException("Technical Specification does not exists"));
        return technicalSpecificationRespository.save(technicalSpecification);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        technicalSpecificationRespository.findById(id)
                .orElseThrow(() -> new NotFoundException("Technical Specification does not exists"));
        technicalSpecificationRespository.deleteById(id);
    }

    @Override
    public void delete(TechnicalSpecification technicalSpecification) throws NotFoundException {
        technicalSpecificationRespository.findById(technicalSpecification.getId())
                .orElseThrow(() -> new NotFoundException("Technical Specification does not exists"));
        technicalSpecificationRespository.delete(technicalSpecification);
    }

    @Override
    public List<TechnicalSpecification> findAll() {
        return technicalSpecificationRespository.findAll();
    }
}
