package ar.com.healthyapple.crm_web.service.Product;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Product.ComponentProfile;
import ar.com.healthyapple.crm_web.repository.Product.ComponentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ComponentProfileServiceImpl implements ComponentProfileService {

    private ComponentProfileRepository repository;

    @Autowired
    public ComponentProfileServiceImpl(ComponentProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public ComponentProfile create(ComponentProfile componentProfile) throws AlreadyExistException {
        if (repository.findByTypeIgnoreCase(componentProfile.getType()).isPresent()) {
            throw new AlreadyExistException("Component Profile already exists.");
        } else {
            return repository.save(componentProfile);
        }
    }

    @Override
    public ComponentProfile read(Long id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("The component profile does not exists."));
    }

    @Override
    public ComponentProfile update(ComponentProfile componentProfile) throws NotFoundException {
        repository.findById(componentProfile.getId()).orElseThrow(() -> new NotFoundException("The component profile does not exists."));
        return repository.save(componentProfile);
    }

    @Override
    public List<ComponentProfile> findAll() {
        return repository.findAll();
    }

    @Override
    public Map<Long, String> getProfileIdAndNames() {
        return repository.getProfilesNames();
    }
}
