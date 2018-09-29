package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Component;
import ar.com.healthyapple.crm_web.repository.ComponentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicalSpecificationItemServiceImpl implements TechnicalSpecificationItemService {

    private ComponentRespository componentRespository;

    @Autowired
    public TechnicalSpecificationItemServiceImpl(ComponentRespository componentRespository) {
        this.componentRespository = componentRespository;
    }

    @Override
    public Component create(Component component) throws AlreadyExistException {
//        if (componentRespository.findByComponentType(
//                                                                    component.getName()).isPresent()) {
//            throw new AlreadyExistException("Technical Specification Item already Exists");
//        } else {
//            return componentRespository.save(component);
//        }
        return null;
    }

    @Override
    public Component read(Long id) throws NotFoundException {
        return componentRespository.findById(id)
                .orElseThrow(() -> new NotFoundException("Technical Specification Item does not exists"));
    }

    @Override
    public Component update(Component component) throws NotFoundException {
        componentRespository.findById(component.getId())
                .orElseThrow(() -> new NotFoundException("Technical Specification Item does not exists"));
        return componentRespository.save(component);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        componentRespository.findById(id)
                .orElseThrow(() -> new NotFoundException("Technical Specification Item does not exists"));
        componentRespository.deleteById(id);
    }

    @Override
    public void delete(Component component) throws NotFoundException {
        componentRespository.findById(component.getId())
                .orElseThrow(() -> new NotFoundException("Technical Specification Item does not exists"));
        componentRespository.delete(component);
    }

    @Override
    public List<Component> findAll() {
        return componentRespository.findAll();
    }
}
