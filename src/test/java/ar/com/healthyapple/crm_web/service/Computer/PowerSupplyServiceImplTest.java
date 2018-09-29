package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Computer.PowerSupply;
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
public class PowerSupplyServiceImplTest {

//    private static final Long ID = 1L;
//
//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//
//    @TestConfiguration
//    static class PowerSupplyServiceTestContextConfiguration {
//
//        @Bean
//        public PowerSupplyService PowerSupplyService(PowerSupplyRepository powerSupplyRepository) {
//            return new PowerSupplyServiceImpl(powerSupplyRepository);
//        }
//    }
//
//    @Autowired
//    private PowerSupplyService powerSupplyService;
//
//    @MockBean
//    private PowerSupplyRepository powerSupplyRepository;
//
//    private PowerSupply powerSupplyRequest;
//
//    private PowerSupply powerSupplyResponse;
//
//    @Before
//    public void setUp() throws Exception {
//        powerSupplyRequest = new PowerSupply("Seasonic", "AAA", "GT11", 500);
//        powerSupplyResponse = new PowerSupply("Seasonic", "AAA","GT11", 500);
//        powerSupplyResponse.setId(1L);
//    }
//
//    @Test
//    public void create() throws Exception{
//        when(powerSupplyRepository.findByPartNumberIgnoreCase(powerSupplyRequest.getPartNumber()))
//                .thenReturn(Optional.empty());
//        when(powerSupplyRepository.save(powerSupplyRequest))
//                .thenReturn(powerSupplyResponse);
//        PowerSupply result = powerSupplyService.create(powerSupplyRequest);
//        assertThat(result).isEqualTo(powerSupplyResponse);
//    }
//
//    @Test
//    public void createDuplicateShouldThrowAlreadyExistsException() throws Exception {
//        thrown.expect(AlreadyExistException.class);
//        when(powerSupplyRepository.findByPartNumberIgnoreCase(powerSupplyRequest.getPartNumber()))
//                .thenReturn(Optional.of(powerSupplyResponse));
//        powerSupplyService.create(powerSupplyRequest);
//    }
//
//    @Test
//    public void read() {
//        when(powerSupplyRepository.findById(powerSupplyRequest.getId()))
//                .thenReturn(Optional.of(powerSupplyResponse));
//        PowerSupply result = powerSupplyService.read(powerSupplyRequest.getId());
//        assertThat(result).isEqualTo(powerSupplyResponse);
//    }
//
//    @Test
//    public void readNonExistentIdShouldThrowNotFoundException() throws Exception {
//        thrown.expect(NotFoundException.class);
//        when(powerSupplyRepository.findById(powerSupplyRequest.getId()))
//                .thenReturn(Optional.empty());
//        powerSupplyService.read(powerSupplyRequest.getId());
//    }
//
//    @Test
//    public void update() {
//        when(powerSupplyRepository.findById(powerSupplyRequest.getId()))
//                .thenReturn(Optional.of(powerSupplyResponse));
//        when(powerSupplyRepository.save(powerSupplyRequest))
//                .thenReturn(powerSupplyResponse);
//        PowerSupply result = powerSupplyService.update(powerSupplyRequest);
//        assertThat(result).isEqualTo(powerSupplyResponse);
//    }
//
//    @Test
//    public void updateByNonExistentResourceShouldThrowNotFoundException() {
//        thrown.expect(NotFoundException.class);
//        when(powerSupplyRepository.findById(powerSupplyRequest.getId()))
//                .thenReturn(Optional.empty());
//        powerSupplyService.update(powerSupplyRequest);
//    }
//
//    @Test
//    public void deleteById() {
//        when(powerSupplyRepository.findById(powerSupplyRequest.getId()))
//                .thenReturn(Optional.of(powerSupplyResponse));
//        doNothing().when(powerSupplyRepository).deleteById(powerSupplyRequest.getId());
//        powerSupplyService.deleteById(powerSupplyRequest.getId());
//    }
//
//    @Test
//    public void deleteByNonExistentIdShouldThrowNotFoundException() {
//        thrown.expect(NotFoundException.class);
//        when(powerSupplyRepository.findById(powerSupplyRequest.getId()))
//                .thenReturn(Optional.empty());
//        powerSupplyService.deleteById(powerSupplyRequest.getId());
//    }
//
//    @Test
//    public void deleteByResource() {
//        when(powerSupplyRepository.findById(powerSupplyRequest.getId()))
//                .thenReturn(Optional.of(powerSupplyResponse));
//        doNothing().when(powerSupplyRepository).delete(powerSupplyRequest);
//        powerSupplyService.delete(powerSupplyRequest);
//    }
//
//    @Test
//    public void deleteByNonExistenResourceShouldThrowNotFoundException() {
//        thrown.expect(NotFoundException.class);
//        when(powerSupplyRepository.findById(powerSupplyRequest.getId()))
//                .thenReturn(Optional.empty());
//        doNothing().when(powerSupplyRepository).delete(powerSupplyRequest);
//        powerSupplyService.delete(powerSupplyRequest);
//    }
//
//    @Test
//    public void findByBrandAndModel() {
//        when(powerSupplyRepository.findByBrandAndModelAllIgnoreCase(powerSupplyRequest.getBrand(), powerSupplyRequest.getModel()))
//                .thenReturn(Optional.of(powerSupplyResponse));
//        PowerSupply result = powerSupplyService.findByBrandAndModel(powerSupplyRequest.getBrand(), powerSupplyRequest.getModel());
//        assertThat(result).isEqualTo(powerSupplyResponse);
//    }
//
//    @Test
//    public void findByNonExistentBrandAndModelShouldThrowNotFoundException() {
//        thrown.expect(NotFoundException.class);
//        when(powerSupplyRepository.findByBrandAndModelAllIgnoreCase(powerSupplyRequest.getBrand(), powerSupplyRequest.getModel()))
//                .thenReturn(Optional.empty());
//        powerSupplyService.findByBrandAndModel(powerSupplyRequest.getBrand(), powerSupplyRequest.getModel());
//    }
//
//    @Test
//    public void findByPartNumberShouldReturnResource() {
//        when(powerSupplyRepository.findByPartNumberIgnoreCase(powerSupplyRequest.getPartNumber())).thenReturn(Optional.of(powerSupplyResponse));
//        PowerSupply result = powerSupplyService.findByPartNumber(powerSupplyRequest.getPartNumber());
//        assertThat(result).isEqualTo(powerSupplyResponse);
//    }
//
//    @Test
//    public void findByNonExistentPartNumberShouldReturnResource() {
//        thrown.expect(NotFoundException.class);
//        when(powerSupplyRepository.findByPartNumberIgnoreCase(powerSupplyRequest.getPartNumber())).thenReturn(Optional.empty());
//        PowerSupply result = powerSupplyService.findByPartNumber(powerSupplyRequest.getPartNumber());
//        assertThat(result).isEqualTo(powerSupplyResponse);
//    }
//
//    @Test
//    public void findAll() {
//        List<PowerSupply> powerSupplyList = new ArrayList<>();
//        powerSupplyList.add(new PowerSupply("Seasonic", "GT11", "AAA",500));
//        powerSupplyList.add(new PowerSupply("Seasonic", "GT11", "AAA",500));
//        powerSupplyList.add(new PowerSupply("Seasonic", "GT11", "AAA",500));
//        powerSupplyList.add(new PowerSupply("Seasonic", "GT11", "AAA",500));
//        when(powerSupplyRepository.findAll()).thenReturn(powerSupplyList);
//        List<PowerSupply> result =powerSupplyService.findAll();
//        assertThat(result).isEqualTo(powerSupplyList);
//    }

}