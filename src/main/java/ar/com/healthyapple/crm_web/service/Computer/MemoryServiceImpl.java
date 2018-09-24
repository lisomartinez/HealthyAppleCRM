package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Computer.Memory;
import ar.com.healthyapple.crm_web.repository.Computer.MemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MemoryServiceImpl implements MemoryService {

    private MemoryRepository memoryRepository;

    @Autowired
    public MemoryServiceImpl(MemoryRepository memoryRepository) {
        this.memoryRepository = memoryRepository;
    }

    @Override
    @Transactional
    public Memory create(Memory memory) throws AlreadyExistException {
        if (memoryRepository.findByPartNumberIgnoreCase(memory.getPartNumber()).isPresent()) {
            throw new AlreadyExistException("Memory already exists");
        } else {
            return memoryRepository.save(memory);
        }
    }
    @Override
    public Memory read(Long id) throws NotFoundException {
        return memoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Memory does not exist. id: " + id));
    }

    @Override
    public Memory update(Memory memory) throws NotFoundException {
        memoryRepository.findById(memory.getId()).orElseThrow(() -> new NotFoundException("Memory does not exist"));
        return memoryRepository.save(memory);
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws NotFoundException {
        memoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Memory does not exist. id: " + id));
        memoryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(Memory memory) throws NotFoundException {
        memoryRepository.findById(memory.getId()).orElseThrow(() -> new NotFoundException("Memory does not exist. id: " + memory.getId()));
        memoryRepository.delete(memory);
    }

    @Override
    public Memory findMemoryByBrandAndModel(String brand, String model)  throws NotFoundException{
        return memoryRepository.findMemoryByBrandAndModelAllIgnoreCase(brand, model)
                .orElseThrow(() -> new NotFoundException("Memory does not exist"));
    }
    @Override
    public List<Memory> findAll() {
        return memoryRepository.findAll();
    }

    @Override
    public Memory findByPartNumber(String partNumber) throws NotFoundException {
        return memoryRepository.findByPartNumberIgnoreCase(partNumber).orElseThrow(() -> new NotFoundException("Memory does not exist"));
    }
}
