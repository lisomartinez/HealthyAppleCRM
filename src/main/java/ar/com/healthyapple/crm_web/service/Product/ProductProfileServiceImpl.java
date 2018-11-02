package ar.com.healthyapple.crm_web.service.Product;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.exceptions.PageDoesNotExistException;
import ar.com.healthyapple.crm_web.model.Product.ComponentProfile;
import ar.com.healthyapple.crm_web.model.Product.ProductProfile;
import ar.com.healthyapple.crm_web.repository.Product.ProductProfileRepository;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProductProfileServiceImpl implements ProductProfileService {

    private ProductProfileRepository repository;


    @Autowired
    public ProductProfileServiceImpl(ProductProfileRepository repository) {
        this.repository = repository;
    }


    @Override
    public ProductProfile create(ProductProfile productProfile) throws AlreadyExistException {
        if (repository.findByTypeIgnoreCase(productProfile.getType()).isPresent()) {
            throw new AlreadyExistException("The Product Profile already exists.");
        }
        return repository.save(productProfile);
    }

    @Override
    public ProductProfile read(Long id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("The Product Profile does not exist."));
    }

    @Override
    public ProductProfile readByName(String name) throws NotFoundException {
        return repository.findByTypeIgnoreCase(name)
                .orElseThrow(() -> new NotFoundException("The Product Profile does not exist."));
    }


    @Override
    public Map<Long, String> getProfileIdAndTypes() throws NotFoundException {
        return repository.getProfilesNames();
    }

    @Override
    public List<ProductProfile> findAll() throws PageDoesNotExistException {
        return repository.findAll();
    }

    @Override
    public ProductProfile update(ProductProfile productProfile) throws NotFoundException {
        repository.findById(productProfile.getId()).orElseThrow(() -> new NotFoundException("The Product Profile does not exist."));
        return repository.save(productProfile);
    }

}
