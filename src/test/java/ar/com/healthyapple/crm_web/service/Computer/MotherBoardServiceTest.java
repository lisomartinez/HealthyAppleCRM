package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Computer.MotherBoard;

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
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class MotherBoardServiceTest {


    private static final Long ID = 1L;
//
//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//
//    @TestConfiguration
//
//    static class MotherBoardServiceTestContextConfiguration {
//
//        @Bean
//        public MotherBoardService motherBoardService(MotherBoardRepository motherBoardRepository) {
//            return new MotherBoardServiceImpl(motherBoardRepository);
//        }
//    }
//
//    @Autowired
//    private MotherBoardService motherBoardService;
//
//    @MockBean
//    private MotherBoardRepository motherBoardRepository;
//
//    private MotherBoard motherBoardRequest;
//
//    private MotherBoard motherBoardResponse;
//
//    @Before
//    public void setUp() throws Exception {
//        motherBoardRequest = new MotherBoard("Intel", "B85m", "AAA","775");
//        motherBoardResponse = new MotherBoard("Intel", "B85m","AAA", "775");
//    }
//
//    @Test
//    public void create() throws AlreadyExistException {
//        when(motherBoardRepository.findByPartNumberIgnoreCase(motherBoardRequest.getPartNumber()))
//                .thenReturn(Optional.empty());
//        when(motherBoardRepository.save(motherBoardRequest)).thenReturn(motherBoardResponse);
//        MotherBoard mb = motherBoardService.create(motherBoardRequest);
//        assertThat(mb).isEqualTo(motherBoardResponse);
//    }
//
//    @Test
//    public void createDuplicateShouldReturnAlreadyExistsException() throws AlreadyExistException{
//        thrown.expect(AlreadyExistException.class);
//        when(motherBoardRepository.findByPartNumberIgnoreCase(motherBoardRequest.getPartNumber()))
//                .thenReturn(Optional.of(motherBoardResponse));
//        when(motherBoardRepository.save(motherBoardRequest)).thenReturn(motherBoardResponse);
//        motherBoardService.create(motherBoardRequest);
//    }
//
//    @Test
//    public void read() throws Exception {
//        when(motherBoardRepository.findById(ID)).thenReturn(Optional.of(motherBoardResponse));
//        MotherBoard mb = motherBoardService.read(ID);
//        assertThat(mb).isEqualTo(motherBoardResponse);
//    }
//
//    @Test
//    public void readNonExistentShouldThrowNotFound() throws Exception {
//        thrown.expect(NotFoundException.class);
//        when(motherBoardRepository.findById(ID)).thenReturn(Optional.empty());
//        motherBoardService.read(ID);
//    }
//
//    @Test
//    public void update() throws Exception {
//        when(motherBoardRepository.findById(motherBoardRequest.getId()))
//                .thenReturn(Optional.of(motherBoardResponse));
//        when(motherBoardRepository.save(motherBoardRequest)).thenReturn(motherBoardResponse);
//        MotherBoard mb = motherBoardService.update(motherBoardRequest);
//        assertThat(mb).isEqualTo(motherBoardResponse);
//    }
//
//    @Test
//    public void updateNonExistentShouldThrowNotFoundException() throws NotFoundException {
//        thrown.expect(NotFoundException.class);
//        when(motherBoardRepository.findById(motherBoardRequest.getId()))
//                .thenReturn(Optional.empty());
//        motherBoardService.update(motherBoardRequest);
//    }
//
//    @Test
//    public void deleteById() throws Exception {
//        when(motherBoardRepository.findById(ID))
//                .thenReturn(Optional.of(motherBoardResponse));
//        motherBoardService.deleteById(ID);
//    }
//
//    @Test
//    public void deleteByNonExistentShouldThrowNotFoundException() throws Exception {
//        thrown.expect(NotFoundException.class);
//        when(motherBoardRepository.findById(ID))
//                .thenReturn(Optional.empty());
//        motherBoardService.deleteById(ID);
//    }
//
//    @Test
//    public void delete() throws Exception {
//        when(motherBoardRepository.findById(motherBoardRequest.getId()))
//                .thenReturn(Optional.of(motherBoardResponse));
//        motherBoardService.delete(motherBoardRequest);
//    }
//
//    @Test
//    public void deleteNonExistentResourceShouldThrowNotFoundException() throws Exception {
//        thrown.expect(NotFoundException.class);
//        when(motherBoardRepository.findById(motherBoardRequest.getId()))
//                .thenReturn(Optional.empty());
//        motherBoardService.delete(motherBoardRequest);
//    }
//
//    @Test
//    public void findByBrandAndModel() {
//        when(motherBoardRepository.findByBrandAndModelAllIgnoreCase(motherBoardRequest.getBrand(), motherBoardRequest.getModel()))
//                .thenReturn(Optional.of(motherBoardResponse));
//        MotherBoard mb = motherBoardService.findByBrandAndModel(motherBoardRequest.getBrand(), motherBoardRequest.getModel());
//        assertThat(mb).isEqualTo(motherBoardResponse);
//    }
//
//    @Test
//    public void findNonExistentBrandAndModelShouldThrowNotFoundException() {
//        thrown.expect(NotFoundException.class);
//        when(motherBoardRepository.findByBrandAndModelAllIgnoreCase(motherBoardRequest.getBrand(), motherBoardRequest.getModel()))
//                .thenReturn(Optional.empty());
//         motherBoardService.findByBrandAndModel(motherBoardRequest.getBrand(), motherBoardRequest.getModel());
//    }
//
//    @Test
//    public void findByPartNumberShouldReturnResource() {
//        when(motherBoardRepository.findByPartNumberIgnoreCase(motherBoardRequest.getPartNumber())).thenReturn(Optional.of(motherBoardResponse));
//        MotherBoard result = motherBoardService.findByPartNumber(motherBoardRequest.getPartNumber());
//        assertThat(result).isEqualTo(motherBoardResponse);
//    }
//
//    @Test
//    public void findByNonExistentPartNumberShouldReturnResource() {
//        thrown.expect(NotFoundException.class);
//        when(motherBoardRepository.findByPartNumberIgnoreCase(motherBoardRequest.getPartNumber())).thenReturn(Optional.empty());
//        MotherBoard result = motherBoardService.findByPartNumber(motherBoardRequest.getPartNumber());
//        assertThat(result).isEqualTo(motherBoardResponse);
//    }
//
//    @Test
//    public void findAll() {
//        List<MotherBoard> motherBoardList = new ArrayList<>();
//        motherBoardList.add(new MotherBoard("Intel", "B85m", "AAA","775"));
//        motherBoardList.add(new MotherBoard("Intel", "B85m", "AAA","775"));
//        motherBoardList.add(new MotherBoard("Intel", "B85m", "AAA","775"));
//        motherBoardList.add(new MotherBoard("Intel", "B85m", "AAA","775"));
//        when(motherBoardRepository.findAll()).thenReturn(motherBoardList);
//        List<MotherBoard> result = motherBoardService.findAll();
//        assertThat(result).isEqualTo(motherBoardList);
//    }

 }
