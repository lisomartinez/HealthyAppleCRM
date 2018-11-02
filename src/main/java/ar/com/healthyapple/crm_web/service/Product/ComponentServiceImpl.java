package ar.com.healthyapple.crm_web.service.Product;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.exceptions.PageDoesNotExistException;
import ar.com.healthyapple.crm_web.model.Product.Component;
import ar.com.healthyapple.crm_web.repository.Product.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ComponentServiceImpl implements ComponentService {

    private ComponentRepository componentRepository;

    @Autowired
    public ComponentServiceImpl(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    @Override
    @Transactional
    public Component create(Component component) throws AlreadyExistException {
        if (componentRepository.findByDescriptionIgnoreCase(component.getDescription()).isPresent()) {
            throw new AlreadyExistException("The  Component already exists.");
        } else {
            return componentRepository.save(component);
        }
    }

    @Override
    public Component read(Long id) throws NotFoundException {
        return componentRepository.findById(id).orElseThrow(() -> new NotFoundException(" Component not found."));
    }

    @Override
    @Transactional
    public Component update(Component component) throws NotFoundException {
        componentRepository.findById(component.getId()).orElseThrow(() -> new NotFoundException("Component not found."));
        return componentRepository.save(component);
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws NotFoundException {
        componentRepository.findById(id).orElseThrow(() -> new NotFoundException("Component not found."));
        componentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(Component component) throws NotFoundException {
        componentRepository.findById(component.getId()).orElseThrow(() -> new NotFoundException("Component not found."));
        componentRepository.delete(component);
    }


    @Override
    public Page<Component> findAll(Pageable pageable) throws PageDoesNotExistException {
        return componentRepository.findAll(pageable);
    }
}
