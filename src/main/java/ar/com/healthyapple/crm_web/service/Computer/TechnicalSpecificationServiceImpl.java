package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.TechnicalSpecification;
import ar.com.healthyapple.crm_web.repository.TechnicalSpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicalSpecificationServiceImpl implements TechnicalSpecificationService {

    private TechnicalSpecificationRepository technicalSpecificationRepository;

    @Autowired
    public TechnicalSpecificationServiceImpl(TechnicalSpecificationRepository technicalSpecificationRepository) {
        this.technicalSpecificationRepository = technicalSpecificationRepository;
    }

    @Override
    public TechnicalSpecification create(TechnicalSpecification technicalSpecification) throws AlreadyExistException {
            return technicalSpecificationRepository.save(technicalSpecification);
    }

    @Override
    public TechnicalSpecification read(Long id) throws NotFoundException {
        return technicalSpecificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Technical Specification does not exists"));
    }

    @Override
    public TechnicalSpecification update(TechnicalSpecification technicalSpecification) throws NotFoundException {
        technicalSpecificationRepository.findById(technicalSpecification.getId())
                .orElseThrow(() -> new NotFoundException("Technical Specification does not exists"));
        return technicalSpecificationRepository.save(technicalSpecification);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        technicalSpecificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Technical Specification does not exists"));
        technicalSpecificationRepository.deleteById(id);
    }

    @Override
    public void delete(TechnicalSpecification technicalSpecification) throws NotFoundException {
        technicalSpecificationRepository.findById(technicalSpecification.getId())
                .orElseThrow(() -> new NotFoundException("Technical Specification does not exists"));
        technicalSpecificationRepository.delete(technicalSpecification);
    }

    @Override
    public List<TechnicalSpecification> findAll() {
        return technicalSpecificationRepository.findAll();
    }
}
