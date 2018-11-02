package ar.com.healthyapple.crm_web.service.Sale;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Sale.ProductService;
import ar.com.healthyapple.crm_web.repository.Service.ServiceRepository;

import javax.transaction.Transactional;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    private ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    @Transactional
    public ProductService create(ProductService productService) throws AlreadyExistException {
        if (serviceRepository.findByProductAndAndName(productService.getProduct(), productService.getName()).isPresent()) {
            throw new AlreadyExistException("ProductService already exists");
        }
        return serviceRepository.save(productService);
    }

    @Override
    public ProductService read(Long id) throws NotFoundException {
        return serviceRepository.findById(id).orElseThrow(() -> new NotFoundException("ProductService not found"));
    }

    @Override
    @Transactional
    public ProductService update(ProductService productService) throws NotFoundException {
        serviceRepository.findById(productService.getId()).orElseThrow(() -> new NotFoundException("ProductService not found"));
        return serviceRepository.save(productService);
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws NotFoundException {
        serviceRepository.findById(id).orElseThrow(() -> new NotFoundException("ProductService not found"));
        serviceRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(ProductService productService) throws NotFoundException {
        serviceRepository.findById(productService.getId()).orElseThrow(() -> new NotFoundException("ProductService not found"));
        serviceRepository.delete(productService);
    }
}
