package ar.com.healthyapple.crm_web.service.Product;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.exceptions.PageDoesNotExistException;
import ar.com.healthyapple.crm_web.model.Product.Specification;
import ar.com.healthyapple.crm_web.repository.Product.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    private SpecificationRepository specificationRepository;

    @Autowired
    public SpecificationServiceImpl(SpecificationRepository specificationRepository) {
        this.specificationRepository = specificationRepository;
    }

    @Override
    @Transactional
    public Specification create(Specification specification) throws AlreadyExistException {
        return specificationRepository.save(specification);
    }

    @Override
    public Specification read(Long id) throws NotFoundException {
        return specificationRepository.findById(id).orElseThrow(() -> new NotFoundException(" Specification not found."));
    }

    @Override
    @Transactional
    public Specification update(Specification specification) throws NotFoundException {
        specificationRepository.findById(specification.getId()).orElseThrow(() -> new NotFoundException("Specification not found."));
        return specificationRepository.save(specification);
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws NotFoundException {
        specificationRepository.findById(id).orElseThrow(() -> new NotFoundException("Specification not found."));
        specificationRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(Specification specification) throws NotFoundException {
        specificationRepository.findById(specification.getId()).orElseThrow(() -> new NotFoundException("Specification not found."));
        specificationRepository.delete(specification);
    }

    @Override
    @Transactional
    public Specification changeName(Long id, @NotEmpty String name) throws NotFoundException {
        Specification specification = specificationRepository.findById(id).orElseThrow(() -> new NotFoundException("Specification not found."));
        specification.setName(name);
        return specificationRepository.save(specification);
    }

    @Override
    public Specification changeDescription(Long id, @NotEmpty String description) throws NotFoundException {
        Specification specification = specificationRepository.findById(id).orElseThrow(() -> new NotFoundException("Specification not found."));
        specification.setDescription(description);
        return specificationRepository.save(specification);
    }

    @Override
    public Page<Specification> findAll(Pageable pageable) throws PageDoesNotExistException {
        return specificationRepository.findAll(pageable);
    }
}
