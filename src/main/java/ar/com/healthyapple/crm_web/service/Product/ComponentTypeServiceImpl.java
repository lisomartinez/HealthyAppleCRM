package ar.com.healthyapple.crm_web.service.Product;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.exceptions.PageDoesNotExistException;
import ar.com.healthyapple.crm_web.model.Product.ComponentType;
import ar.com.healthyapple.crm_web.repository.Product.ComponentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

public class ComponentTypeServiceImpl implements ComponentTypeService {


    private ComponentTypeRepository componentTypeRepository;

    @Autowired
    public ComponentTypeServiceImpl(ComponentTypeRepository componentTypeRepository) {
        this.componentTypeRepository = componentTypeRepository;
    }

    @Override
    @Transactional
    public ComponentType create(ComponentType componentType) throws AlreadyExistException {

        if (componentTypeRepository.findByNameIgnoreCase(componentType.getName()).isPresent()) {
            throw new AlreadyExistException("The  ComponentType already exists.");
        } else {
            return componentTypeRepository.save(componentType);
        }
    }

    @Override
    public ComponentType read(Long id) throws NotFoundException {
        return componentTypeRepository.findById(id).orElseThrow(() -> new NotFoundException(" ComponentType not found."));
    }

    @Override
    @Transactional
    public ComponentType update(ComponentType componentType) throws NotFoundException {
        componentTypeRepository.findById(componentType.getId()).orElseThrow(() -> new NotFoundException("ComponentType not found."));
        return componentTypeRepository.save(componentType);
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws NotFoundException {
        componentTypeRepository.findById(id).orElseThrow(() -> new NotFoundException("ComponentType not found."));
        componentTypeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(ComponentType componentType) throws NotFoundException {
        componentTypeRepository.findById(componentType.getId()).orElseThrow(() -> new NotFoundException("ComponentType not found."));
        componentTypeRepository.delete(componentType);
    }


    @Override
    public Page<ComponentType> findAll(Pageable pageable) throws PageDoesNotExistException {
        return componentTypeRepository.findAll(pageable);
    }
}
