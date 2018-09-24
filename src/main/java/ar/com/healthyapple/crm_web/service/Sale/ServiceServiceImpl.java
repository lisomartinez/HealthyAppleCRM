package ar.com.healthyapple.crm_web.service.Sale;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Service;
import ar.com.healthyapple.crm_web.repository.ServiceRepository;

import javax.transaction.Transactional;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    private ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    @Transactional
    public Service create(Service service) throws AlreadyExistException {
        if (serviceRepository.findByProductAndAndName(service.getProduct(), service.getName()).isPresent()) {
            throw new AlreadyExistException("Service already exists");
        }
        return serviceRepository.save(service);
    }

    @Override
    public Service read(Long id) throws NotFoundException {
        return serviceRepository.findById(id).orElseThrow(() -> new NotFoundException("Service not found"));
    }

    @Override
    @Transactional
    public Service update(Service service) throws NotFoundException {
        serviceRepository.findById(service.getId()).orElseThrow(() -> new NotFoundException("Service not found"));
        return serviceRepository.save(service);
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws NotFoundException {
        serviceRepository.findById(id).orElseThrow(() -> new NotFoundException("Service not found"));
        serviceRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(Service service) throws NotFoundException {
        serviceRepository.findById(service.getId()).orElseThrow(() -> new NotFoundException("Service not found"));
        serviceRepository.delete(service);
    }
}
