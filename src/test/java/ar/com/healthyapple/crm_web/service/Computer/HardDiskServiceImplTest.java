package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Computer.HardDrive;
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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class HardDiskServiceImplTest {
//
//    private static final Long ID = 1L;
//
//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//
//    @TestConfiguration
//    static class HardDiskServiceImplTestContextConfiguration {
//        @Bean
//        public HardDiskService hardDiskService(HardDriveRepository hardDriveRepository) {
//            return new HardDiskServiceImpl(hardDriveRepository);
//        }
//    }
//
//    @MockBean
//    private HardDriveRepository hardDriveRepository;
//
//    @Autowired
//    private HardDiskService hardDiskService;
//
//    private HardDrive hardDriveRequest;
//    private HardDrive hardDriveResponse;
//
//    @Before
//    public void setUp() throws Exception {
//        hardDriveRequest = new HardDrive("Samsung", "860 EVO", "AAA","ssd", "500");
//        hardDriveResponse = new HardDrive("Samsung", "860 EVO", "AAA","ssd", "500");
//        hardDriveResponse.setId(ID);
//    }
//
//    @Test
//    public void create() throws AlreadyExistException {
//        when(hardDriveRepository.findByPartNumberIgnoreCase(hardDriveRequest.getPartNumber())).thenReturn(Optional.empty());
//        when(hardDriveRepository.save(hardDriveRequest)).thenReturn(hardDriveResponse);
//        HardDrive result = hardDiskService.create(new HardDrive("Samsung", "860 EVO", "AAA","ssd", "500"));
//        assertThat(result).isEqualTo(hardDriveResponse);
//    }
//
//    @Test
//    public void createDuplicateShouldThrowAlreadyExistException() throws Exception {
//        thrown.expect(AlreadyExistException.class);
//        when(hardDriveRepository.findByPartNumberIgnoreCase(hardDriveRequest.getPartNumber())).thenReturn(Optional.of(hardDriveResponse));
//        HardDrive result = hardDiskService.create(new HardDrive("Samsung", "860 EVO", "AAA","ssd", "500"));
//    }
//
//    @Test
//    public void readById() throws NotFoundException {
//        Optional<HardDrive> hardDrive = Optional.of(hardDriveResponse);
//        when(hardDriveRepository.findById(ID)).thenReturn(hardDrive);
//        HardDrive result = hardDiskService.readById(ID);
//        assertThat(result).isEqualTo(hardDrive.get());
//    }
//
//    @Test
//    public void readNonExistentIdShouldThrowNotFoundException() throws Exception {
//        thrown.expect(NotFoundException.class);
//        when(hardDriveRepository.findById(ID)).thenReturn(Optional.empty());
//        HardDrive result = hardDiskService.readById(ID);
//    }
//
//
//    @Test
//    public void deleteById() {
//        when(hardDriveRepository.findById(ID)).thenReturn(Optional.of(hardDriveResponse));
//        doNothing().when(hardDriveRepository).deleteById(ID);
//        hardDiskService.deleteById(ID);
//    }
//
//    @Test
//    public void deleteByNonExistentIdShouldThrowNotFoundExeception() {
//        thrown.expect(NotFoundException.class);
//        when(hardDriveRepository.findById(ID)).thenReturn(Optional.empty());
//        doNothing().when(hardDriveRepository).deleteById(ID);
//        hardDiskService.deleteById(ID);
//    }
//
//    @Test
//    public void update() {
//        hardDriveRequest.setId(ID);
//        when(hardDriveRepository.findById(ID)).thenReturn(Optional.of(hardDriveRequest));
//        when(hardDriveRepository.save(hardDriveRequest)).thenReturn(hardDriveResponse);
//        HardDrive result = hardDiskService.update(hardDriveRequest);
//        assertThat(result).isEqualTo(hardDriveResponse);
//    }
//
//    @Test
//    public void updateNonExistentResourceShouldThrowNonFoundException() {
//        thrown.expect(NotFoundException.class);
//        when(hardDriveRepository.findById(ID)).thenReturn(Optional.of(hardDriveRequest));
//        when(hardDriveRepository.save(hardDriveRequest)).thenReturn(hardDriveResponse);
//        HardDrive result = hardDiskService.update(hardDriveRequest);
//    }
//
//    @Test
//    public void delete() {
//        hardDriveRequest.setId(ID);
//        when(hardDriveRepository.findById(ID)).thenReturn(Optional.of(hardDriveRequest));
//        doNothing().when(hardDriveRepository).delete(hardDriveRequest);
//        hardDiskService.delete(hardDriveRequest);
//    }
//
//
//    @Test
//    public void deleteByNonExistentResourceShouldThrowNotFoundExceptio() {
//        thrown.expect(NotFoundException.class);
//        when(hardDriveRepository.findById(ID)).thenReturn(Optional.of(hardDriveRequest));
//        doNothing().when(hardDriveRepository).delete(hardDriveRequest);
//        hardDiskService.delete(hardDriveRequest);
//    }
//
//    @Test
//    public void findHardDriveByBrandAndModel() {
//        when(hardDriveRepository.findHardDriveByBrandAndModelAllIgnoreCase(hardDriveRequest.getBrand(), hardDriveRequest.getModel())).thenReturn(Optional.of(hardDriveResponse));
//        HardDrive hd = hardDiskService.findHardDriveByBrandAndModel(hardDriveRequest.getBrand(), hardDriveRequest.getModel());
//        assertThat(hd).isEqualTo(hardDriveResponse);
//    }
//
//    @Test
//    public void findHarDriveByNonExistentBrandAndModelShouldReturnNotFound() {
//        thrown.expect(NotFoundException.class);
//        when(hardDriveRepository.findHardDriveByBrandAndModelAllIgnoreCase(hardDriveRequest.getBrand(), hardDriveRequest.getModel())).thenReturn(Optional.empty());
//        HardDrive hd = hardDiskService.findHardDriveByBrandAndModel(hardDriveRequest.getBrand(), hardDriveRequest.getModel());
//    }
//
//    @Test
//    public void findByPartNumberShouldReturnResource() {
//        when(hardDriveRepository.findByPartNumberIgnoreCase(hardDriveRequest.getPartNumber())).thenReturn(Optional.of(hardDriveResponse));
//        HardDrive hd = hardDiskService.findByPartNumber(hardDriveRequest.getPartNumber());
//        assertThat(hd).isEqualTo(hardDriveResponse);
//    }
//
//    @Test
//    public void findByNonExistentPartNumberShouldReturnResource() {
//        thrown.expect(NotFoundException.class);
//
//        when(hardDriveRepository.findByPartNumberIgnoreCase(hardDriveRequest.getPartNumber())).thenReturn(Optional.empty());
//        HardDrive hd = hardDiskService.findByPartNumber(hardDriveRequest.getPartNumber());
//        assertThat(hd).isEqualTo(hardDriveResponse);
//    }
//    @Test
//    public void findAll() {
//        List<HardDrive> hardDriveList = new ArrayList<>();
//        hardDriveList.add(new HardDrive("Samsung", "860 EVO", "AAA", "ssd", "500"));
//        hardDriveList.add(new HardDrive("Samsung", "860 EVO", "AAA","ssd", "500"));
//        hardDriveList.add(new HardDrive("Samsung", "860 EVO", "AAA","ssd", "500"));
//        hardDriveList.add(new HardDrive("Samsung", "860 EVO", "AAA","ssd", "500"));
//        when(hardDriveRepository.findAll()).thenReturn(hardDriveList);
//        List<HardDrive> hardDriveList1 = hardDiskService.findAll();
//        assertThat(hardDriveList).isEqualTo(hardDriveList1);
//    }
}