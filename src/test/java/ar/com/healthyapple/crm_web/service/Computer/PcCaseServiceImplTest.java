package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Computer.PcCase;
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
public class PcCaseServiceImplTest {
//
//    private static final Long ID = 1L;
//
//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//
//    @TestConfiguration
//    static class PcCaseServiceTestContextConfiguration {
//
//        @Bean
//        public PcCaseService PcCaseService(PcCaseRepository pcCaseRepository) {
//            return new PcCaseServiceImpl(pcCaseRepository);
//        }
//    }
//
//    @Autowired
//    private PcCaseService pcCaseService;
//
//    @MockBean
//    private PcCaseRepository pcCaseRepository;
//
//    private PcCase pcCaseRequest;
//
//    private PcCase pcCaseResponse;
//
//    @Before
//    public void setUp() throws Exception {
//        pcCaseRequest = new PcCase("Sentey" , "A20", "AAA","ATX");
//        pcCaseResponse = new PcCase("Sentey" , "A20","AAA", "ATX");
//        pcCaseResponse.setId(1L);
//    }
//
//    @Test
//    public void create() throws AlreadyExistException {
//        when(pcCaseRepository.findByPartNumberIgnoreCase(pcCaseRequest.getPartNumber()))
//                .thenReturn(Optional.empty());
//        when(pcCaseRepository.save(pcCaseRequest)).thenReturn(pcCaseResponse);
//        PcCase result = pcCaseService.create(pcCaseRequest);
//
//        assertThat(result).isEqualTo(pcCaseResponse);
//    }
//
//    @Test
//    public void createDuplicateShouldThrowAlreadyExistsException() throws Exception {
//        thrown.expect(AlreadyExistException.class);
//        when(pcCaseRepository.findByPartNumberIgnoreCase(pcCaseRequest.getPartNumber()))
//                .thenReturn(Optional.of(pcCaseResponse));
//        pcCaseService.create(pcCaseRequest);
//    }
//
//    @Test
//    public void read() {
//        pcCaseRequest.setId(1L);
//        when(pcCaseRepository.findById(pcCaseRequest.getId())).thenReturn(Optional.of(pcCaseResponse));
//        PcCase result = pcCaseService.read(ID);
//        assertThat(result).isEqualTo(pcCaseResponse);
//    }
//
//    @Test
//    public void readByNonExistentIdShouldThrowNotFoundException() throws Exception {
//        thrown.expect(NotFoundException.class);
//        when(pcCaseRepository.findById(pcCaseRequest.getId())).thenReturn(Optional.empty());
//        PcCase result = pcCaseService.read(ID);
//    }
//
//    @Test
//    public void update() {
//        pcCaseRequest.setId(1L);
//        when(pcCaseRepository.findById(1L)).thenReturn(Optional.of(pcCaseResponse));
//        PcCase result = pcCaseService.read(pcCaseRequest.getId());
//        assertThat(result).isEqualTo(pcCaseResponse);
//    }
//
//    @Test
//    public void deleteById() {
//        pcCaseRequest.setId(1L);
//        when(pcCaseRepository.findById(pcCaseRequest.getId())).thenReturn(Optional.of(pcCaseResponse));
//        doNothing().when(pcCaseRepository).deleteById(ID);
//        pcCaseService.deleteById(ID);
//    }
//
//    @Test
//    public void deleteByNonExistentIdShouldThrowNotFoundException() throws NotFoundException {
//        thrown.expect(NotFoundException.class);
//        pcCaseRequest.setId(1L);
//        when(pcCaseRepository.findById(pcCaseRequest.getId())).thenReturn(Optional.empty());
//        doNothing().when(pcCaseRepository).deleteById(ID);
//        pcCaseService.deleteById(ID);
//
//    }
//
//    @Test
//    public void delete() {
//        pcCaseRequest.setId(1L);
//        when(pcCaseRepository.findById(pcCaseRequest.getId())).thenReturn(Optional.of(pcCaseResponse));
//        doNothing().when(pcCaseRepository).delete(pcCaseRequest);
//        pcCaseService.deleteById(ID);
//    }
//
//    @Test
//    public void deleteByNonExistentResourceShouldThrowNotFoundException() throws Exception {
//        thrown.expect(NotFoundException.class);
//        when(pcCaseRepository.findById(pcCaseRequest.getId())).thenReturn(Optional.empty());
//        doNothing().when(pcCaseRepository).delete(pcCaseRequest);
//        pcCaseService.delete(pcCaseRequest);
//    }
//
//    @Test
//    public void findByBrandAndModel() {
//        when(pcCaseRepository.findByBrandAndModelAllIgnoreCase(pcCaseRequest.getBrand(), pcCaseRequest.getModel()))
//                .thenReturn(Optional.of(pcCaseResponse));
//        PcCase result = pcCaseService.findByBrandAndModel(pcCaseRequest.getBrand(), pcCaseRequest.getModel());
//        assertThat(result).isEqualTo(pcCaseResponse);
//    }
//
//    @Test
//    public void findByNonExistentBrandAndModelShouldThrowNotFound() throws Exception {
//        thrown.expect(NotFoundException.class);
//        when(pcCaseRepository.findByBrandAndModelAllIgnoreCase(pcCaseRequest.getBrand(), pcCaseRequest.getModel()))
//                .thenReturn(Optional.empty());
//        pcCaseService.findByBrandAndModel(pcCaseRequest.getBrand(), pcCaseRequest.getModel());
//    }
//
//    @Test
//    public void findByPartNumberShouldReturnResource() {
//        when(pcCaseRepository.findByPartNumberIgnoreCase(pcCaseRequest.getPartNumber())).thenReturn(Optional.of(pcCaseResponse));
//        PcCase result = pcCaseService.findByPartNumber(pcCaseRequest.getPartNumber());
//        assertThat(result).isEqualTo(pcCaseResponse);
//    }
//
//    @Test
//    public void findByNonExistentPartNumberShouldReturnResource() {
//        thrown.expect(NotFoundException.class);
//        when(pcCaseRepository.findByPartNumberIgnoreCase(pcCaseRequest.getPartNumber())).thenReturn(Optional.empty());
//        PcCase result = pcCaseService.findByPartNumber(pcCaseRequest.getPartNumber());
//        assertThat(result).isEqualTo(pcCaseResponse);
//    }
//
//    @Test
//    public void findAll() {
//        List<PcCase> pcCaseList = new ArrayList<>();
//        pcCaseList.add(new PcCase("Sentey" , "A20", "AAA","ATX"));
//        pcCaseList.add(new PcCase("Sentey" , "A20", "AAA","ATX"));
//        pcCaseList.add(new PcCase("Sentey" , "A20", "AAA","ATX"));
//        pcCaseList.add(new PcCase("Sentey" , "A20", "AAA","ATX"));
//        when(pcCaseRepository.findAll())
//                .thenReturn(pcCaseList);
//        List<PcCase> result = pcCaseService.findAll();
//        assertThat(result).isEqualTo(pcCaseList);
//    }

}