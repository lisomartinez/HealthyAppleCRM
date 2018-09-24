package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.TechnicalSpecificationItem;
import ar.com.healthyapple.crm_web.repository.TechnicalSpecificationItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicalSpecificationItemServiceImpl implements TechnicalSpecificationItemService {

    private TechnicalSpecificationItemRepository technicalSpecificationItemRepository;

    @Autowired
    public TechnicalSpecificationItemServiceImpl(TechnicalSpecificationItemRepository technicalSpecificationItemRepository) {
        this.technicalSpecificationItemRepository = technicalSpecificationItemRepository;
    }

    @Override
    public TechnicalSpecificationItem create(TechnicalSpecificationItem technicalSpecificationItem) throws AlreadyExistException {
        if (technicalSpecificationItemRepository.findByNameAndDescriptionAndTechnicalSpecificationItemType(
                                                                    technicalSpecificationItem.getName(),
                                                                    technicalSpecificationItem.getDescription(),
                                                                    technicalSpecificationItem.getTechnicalSpecificationItemType()).isPresent()) {
            throw new AlreadyExistException("Technical Specification Item already Exists");
        } else {
            return technicalSpecificationItemRepository.save(technicalSpecificationItem);
        }
    }

    @Override
    public TechnicalSpecificationItem read(Long id) throws NotFoundException {
        return technicalSpecificationItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Technical Specification Item does not exists"));
    }

    @Override
    public TechnicalSpecificationItem update(TechnicalSpecificationItem technicalSpecificationItem) throws NotFoundException {
        technicalSpecificationItemRepository.findById(technicalSpecificationItem.getId())
                .orElseThrow(() -> new NotFoundException("Technical Specification Item does not exists"));
        return technicalSpecificationItemRepository.save(technicalSpecificationItem);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        technicalSpecificationItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Technical Specification Item does not exists"));
        technicalSpecificationItemRepository.deleteById(id);
    }

    @Override
    public void delete(TechnicalSpecificationItem technicalSpecificationItem) throws NotFoundException {
        technicalSpecificationItemRepository.findById(technicalSpecificationItem.getId())
                .orElseThrow(() -> new NotFoundException("Technical Specification Item does not exists"));
        technicalSpecificationItemRepository.delete(technicalSpecificationItem);
    }

    @Override
    public List<TechnicalSpecificationItem> findAll() {
        return technicalSpecificationItemRepository.findAll();
    }
}
