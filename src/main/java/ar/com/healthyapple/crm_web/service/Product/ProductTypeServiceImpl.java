package ar.com.healthyapple.crm_web.service.Product;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.exceptions.PageDoesNotExistException;
import ar.com.healthyapple.crm_web.model.Product.ProductType;
import ar.com.healthyapple.crm_web.repository.Product.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductTypeServiceImpl implements ProductTypeService {

    private ProductTypeRepository productTypeRepository;

    @Autowired
    public ProductTypeServiceImpl(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    @Transactional
    public ProductType create(ProductType productType) throws AlreadyExistException {
        if (productTypeRepository.findByNameIgnoreCase(productType.getName()).isPresent()) {
            throw new AlreadyExistException("The  ProductType already exists.");
        } else {
            return productTypeRepository.save(productType);
        }
    }

    @Override
    public ProductType read(Long id) throws NotFoundException {
        return productTypeRepository.findById(id).orElseThrow(() -> new NotFoundException(" ProductType not found."));
    }

    @Override
    @Transactional
    public ProductType update(ProductType productType) throws NotFoundException {
        productTypeRepository.findById(productType.getId()).orElseThrow(() -> new NotFoundException("ProductType not found."));
        return productTypeRepository.save(productType);
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws NotFoundException {
        productTypeRepository.findById(id).orElseThrow(() -> new NotFoundException("ProductType not found."));
        productTypeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(ProductType productType) throws NotFoundException {
        productTypeRepository.findById(productType.getId()).orElseThrow(() -> new NotFoundException("ProductType not found."));
        productTypeRepository.delete(productType);
    }


    @Override
    public Page<ProductType> findAll(Pageable pageable) throws PageDoesNotExistException {
        return productTypeRepository.findAll(pageable);
    }
}
