package ar.com.healthyapple.crm_web.service.Computer;


import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Computer.Processor;
import ar.com.healthyapple.crm_web.repository.Computer.ProcessorRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class ProcessorServiceImplTest {
    private static final Long ID = 1L;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @TestConfiguration
    static class ProcessorServiceTestContextConfiguration {

        @Bean
        public ProcessorService ProcessorService(ProcessorRepository processorRepository) {
            return new ProcessorServiceImpl(processorRepository);
        }
    }

    @Autowired
    private ProcessorService processorService;

    @MockBean
    private ProcessorRepository processorRepository;

    private Processor processorRequest;

    private Processor processorResponse;

    @Before
    public void setUp() throws Exception {
        processorRequest = new Processor("Intel", "i3", "AAA",4, "1150", 4000);
        processorResponse = new Processor("Intel", "i3","AAA", 4, "1150", 4000);
        processorResponse.setId(1L);
    }

    @Test
    public void create() throws AlreadyExistException {
        when(processorRepository.findByPartNumberIgnoreCase(processorRequest.getPartNumber()))
                .thenReturn(Optional.empty());
        when(processorRepository.save(processorRequest))
                .thenReturn(processorResponse);
        Processor result = processorService.create(processorRequest);
        assertThat(result).isEqualTo(processorResponse);
    }

    @Test
    public void createDuplicateShouldThrowAlreadyExistsException() throws AlreadyExistException {
        thrown.expect(AlreadyExistException.class);
        when(processorRepository.findByPartNumberIgnoreCase(processorRequest.getPartNumber()))
                .thenReturn(Optional.of(processorResponse));
        processorService.create(processorRequest);
    }

    @Test
    public void read() {
        processorRequest.setId(1L);
        when(processorRepository.findById(processorRequest.getId()))
                .thenReturn(Optional.of(processorResponse));
        Processor result = processorService.read(processorRequest.getId());
        assertThat(result).isEqualTo(processorResponse);
    }

    @Test
    public void readByNonExistentIdShouldThrowNotFoundException() {
        thrown.expect(NotFoundException.class);
        processorRequest.setId(1L);
        when(processorRepository.findById(processorRequest.getId()))
                .thenReturn(Optional.empty());
        processorService.read(processorRequest.getId());    }

    @Test
    public void update() {
        processorRequest.setId(1L);
        when(processorRepository.findById(processorRequest.getId()))
                .thenReturn(Optional.of(processorResponse));
        when(processorRepository.save(processorRequest)).thenReturn(processorResponse);
        Processor result = processorService.update(processorRequest);
        assertThat(result).isEqualTo(processorResponse);


//        when(powerSupplyDao.findById(powerSupplyRequest.getId()))
//                .thenReturn(Optional.of(powerSupplyResponse));
//        when(powerSupplyDao.save(powerSupplyRequest))
//                .thenReturn(powerSupplyResponse);
//        PowerSupply result = powerSupplyService.update(powerSupplyRequest);
//        assertThat(result).isEqualTo(powerSupplyResponse);
    }

    @Test
    public void updateByNonExistentResourceShouldThrowNotFoundException() {
        thrown.expect(NotFoundException.class);
        processorRequest.setId(1L);
        when(processorRepository.findById(processorRequest.getId()))
                .thenReturn(Optional.empty());
        processorService.update(processorRequest);
    }

    @Test
    public void deleteById() {
        processorRequest.setId(1L);
        when(processorRepository.findById(processorRequest.getId()))
                .thenReturn(Optional.of(processorResponse));
        doNothing().when(processorRepository).deleteById(processorRequest.getId());
        processorService.deleteById(processorRequest.getId());
    }

    @Test
    public void deleteByNonExistentIdShouldThrowNotFoundException() {
        thrown.expect(NotFoundException.class);
        processorRequest.setId(1L);
        when(processorRepository.findById(processorRequest.getId()))
                .thenReturn(Optional.empty());
        processorService.deleteById(processorRequest.getId());
    }

    @Test
    public void delete() {
        processorRequest.setId(1L);
        when(processorRepository.findById(processorRequest.getId()))
                .thenReturn(Optional.of(processorResponse));
        doNothing().when(processorRepository).delete(processorRequest);
        processorService.delete(processorRequest);
    }

    @Test
    public void deleteByNonExistentResource() {
        thrown.expect(NotFoundException.class);
        processorRequest.setId(1L);
        when(processorRepository.findById(processorRequest.getId()))
                .thenReturn(Optional.empty());
        doNothing().when(processorRepository).delete(processorRequest);
        processorService.delete(processorRequest);
    }

    @Test
    public void findByBrandAndModel() {
        when(processorRepository.findByBrandAndModelAllIgnoreCase(processorRequest.getBrand(), processorRequest.getModel()))
                .thenReturn(Optional.of(processorResponse));
        Processor result = processorService.findByBrandAndModel(processorRequest.getBrand(), processorRequest.getModel());
        assertThat(result).isEqualTo(processorResponse);
    }

    @Test
    public void findByNonExistentBrandAndModel() {
        thrown.expect(NotFoundException.class);
        when(processorRepository.findByBrandAndModelAllIgnoreCase(processorRequest.getBrand(), processorRequest.getModel()))
                .thenReturn(Optional.empty());
        processorService.findByBrandAndModel(processorRequest.getBrand(), processorRequest.getModel());
    }

    @Test
    public void findByPartNumberShouldReturnResource() {
        when(processorRepository.findByPartNumberIgnoreCase(processorRequest.getPartNumber())).thenReturn(Optional.of(processorResponse));
        Processor result = processorService.findByPartNumber(processorRequest.getPartNumber());
        assertThat(result).isEqualTo(processorResponse);
    }

    @Test
    public void findByNonExistentPartNumberShouldReturnResource() {
        thrown.expect(NotFoundException.class);
        when(processorRepository.findByPartNumberIgnoreCase(processorRequest.getPartNumber())).thenReturn(Optional.empty());
        Processor result = processorService.findByPartNumber(processorRequest.getPartNumber());
        assertThat(result).isEqualTo(processorResponse);
    }
    @Test
    public void findAll() {
        List<Processor> processorList = new ArrayList<>();
        processorList.add(new Processor("Intel", "i3", "AAA", 4, "1150", 4000));
        processorList.add(new Processor("Intel", "i3", "AAA", 4, "1150", 4000));
        processorList.add(new Processor("Intel", "i3", "AAA", 4, "1150", 4000));
        processorList.add(new Processor("Intel", "i3", "AAA", 4, "1150", 4000));
        when(processorRepository.findAll()).thenReturn(processorList);
        List<Processor> result = processorService.findAll();
        assertThat(result).isEqualTo(processorList);
    }

}