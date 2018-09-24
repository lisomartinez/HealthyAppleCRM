package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Computer.MotherBoard;
import ar.com.healthyapple.crm_web.repository.Computer.MotherBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MotherBoardServiceImpl implements MotherBoardService {

    private MotherBoardRepository motherBoardRepository;

    @Autowired
    public MotherBoardServiceImpl(MotherBoardRepository motherBoardRepository) {
        this.motherBoardRepository = motherBoardRepository;
    }

    @Override
    @Transactional
    public MotherBoard create(MotherBoard motherBoard) throws AlreadyExistException {
        if (motherBoardRepository.findByPartNumberIgnoreCase(motherBoard.getPartNumber()).isPresent()) {
            throw new AlreadyExistException("MotherBoard Already exists");
        } else {
            return motherBoardRepository.save(motherBoard);
        }
    }

    @Override
    public MotherBoard read(Long id) throws NotFoundException {
        return motherBoardRepository.findById(id).orElseThrow(() -> new NotFoundException("MotherBoard not found"));
    }

    @Override
    @Transactional
    public MotherBoard update(MotherBoard motherBoard) throws NotFoundException {
        motherBoardRepository.findById(motherBoard.getId()).orElseThrow(() -> new NotFoundException("MotherBoard not found"));
        return motherBoardRepository.save(motherBoard);
    }

    @Override
    @Transactional
    public void delete(MotherBoard motherBoard) throws NotFoundException {
        motherBoardRepository.findById(motherBoard.getId()).orElseThrow(() -> new NotFoundException("MotherBoard not found"));
        motherBoardRepository.delete(motherBoard);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        motherBoardRepository.findById(id).orElseThrow(() -> new NotFoundException("MotherBoard not found"));
        motherBoardRepository.deleteById(id);
    }

    @Override
    public List<MotherBoard> findAll() {
        return motherBoardRepository.findAll();
    }

    @Override
    @Transactional
    public MotherBoard findByBrandAndModel(@NonNull String brand, @NonNull String model) throws NotFoundException {
       return motherBoardRepository.findByBrandAndModelAllIgnoreCase(brand, model)
                .orElseThrow(() -> new NotFoundException("Motherboard not found"));
    }

    @Override
    public MotherBoard findByPartNumber(@NonNull String partNumber) throws NotFoundException{
        return motherBoardRepository.findByPartNumberIgnoreCase(partNumber).orElseThrow(() -> new NotFoundException("Motherboard not found"));
    }
}
