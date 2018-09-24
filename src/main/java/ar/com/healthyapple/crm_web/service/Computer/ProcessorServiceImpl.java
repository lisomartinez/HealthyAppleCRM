package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Computer.Processor;
import ar.com.healthyapple.crm_web.repository.Computer.ProcessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProcessorServiceImpl implements ProcessorService {

    private ProcessorRepository processorRepository;

    @Autowired
    public ProcessorServiceImpl(ProcessorRepository processorRepository) {
        this.processorRepository = processorRepository;
    }

    @Override
    @Transactional
    public Processor create(Processor processor) throws AlreadyExistException {
        if (processorRepository.findByPartNumberIgnoreCase(processor.getPartNumber()).isPresent()) {
            throw new AlreadyExistException("Processor already exists");
        } else {
            return processorRepository.save(processor);
        }
    }

    @Override
    public Processor read(Long id) throws NotFoundException {
        return processorRepository.findById(id).orElseThrow(() -> new NotFoundException("Processor not found"));
    }

    @Override
    @Transactional
    public Processor update(Processor processor) throws NotFoundException {
        processorRepository.findById(processor.getId())
                .orElseThrow(() -> new NotFoundException("Processor not found"));
        return processorRepository.save(processor);
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws NotFoundException {
         processorRepository.findById(id)
                 .orElseThrow(() -> new NotFoundException("Processor not found"));
        processorRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(Processor processor) throws NotFoundException {
        processorRepository.findById(processor.getId())
                .orElseThrow(() -> new NotFoundException("Processor not found"));
        processorRepository.delete(processor);
    }

    @Override
    public List<Processor> findAll() {

        return processorRepository.findAll();
    }

    @Override
    public Processor findByBrandAndModel(String brand, String model) throws NotFoundException {
        return processorRepository.findByBrandAndModelAllIgnoreCase(brand, model)
                .orElseThrow(() -> new NotFoundException("Processor not found"));
    }

    @Override
    public Processor findByPartNumber(String partNumber) throws NotFoundException {
        return processorRepository.findByPartNumberIgnoreCase(partNumber)
                .orElseThrow(() -> new NotFoundException("Processor not found"));
    }
}
