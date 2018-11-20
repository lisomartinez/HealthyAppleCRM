package ar.com.healthyapple.crm_web.service.Product;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.exceptions.PageDoesNotExistException;
import ar.com.healthyapple.crm_web.model.Product.Component;
import ar.com.healthyapple.crm_web.model.Product.Product;
import ar.com.healthyapple.crm_web.repository.Product.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product create(Product product) throws AlreadyExistException {
        return productRepository.save(product);
    }

    @Override
    public Product read(Long id) throws NotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("The component does not exist."));
    }

    @Override
    @Transactional
    public Product update(Product product) throws NotFoundException {
        productRepository.findById(product.getId()).orElseThrow(() -> new NotFoundException("The component does not exist."));
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws NotFoundException {
        productRepository.findById(id).orElseThrow(() -> new NotFoundException("The component does not exist."));
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(Product product) throws NotFoundException {
        productRepository.findById(product.getId()).orElseThrow(() -> new NotFoundException("The component does not exist."));
        productRepository.delete(product);
    }


    @Override
    @Transactional
    public Product updateDescription(Long id, String description) throws NotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("The component does not exist."));
        product.setDescription(description);
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product updateSpecs(Long id, List<Component> specs) throws NotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("The component does not exist."));
        product.setComponents(specs);
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Page<Product> findAll(Pageable pageable) throws PageDoesNotExistException {
        return productRepository.findAll(pageable);
    }


}
