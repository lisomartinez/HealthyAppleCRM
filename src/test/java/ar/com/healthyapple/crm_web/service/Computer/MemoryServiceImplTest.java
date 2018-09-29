package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Computer.Memory;
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
public class MemoryServiceImplTest {
//
//    private static final Long ID = 1L;
//
//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//
//    @TestConfiguration
//    static class MemoryServiceImplTestContextConfiguration {
//
//        @Bean
//        public MemoryService memoryService(MemoryRepository memoryRepository) {
//            return new MemoryServiceImpl(memoryRepository);
//        }
//
//    }
//
//    @Autowired
//    private MemoryService memoryService;
//
//    @MockBean
//    private MemoryRepository memoryRepository;
//
//    private Memory memoryRequest;
//
//    private Memory memoryResponse;
//
//    @Before
//    public void setUp() throws Exception {
//        memoryRequest = new Memory("Kingston", "Saphire", "AAA","DDR4",500, 4);
//        memoryResponse = new Memory("Kingston", "Saphire","AAA", "DDR4",500, 4);
//    }
//
//    @Test
//    public void create() throws Exception {
//        when(memoryRepository.findByPartNumberIgnoreCase(memoryRequest.getPartNumber()))
//                .thenReturn(Optional.empty());
//        when(memoryRepository.save(memoryRequest)).thenReturn(memoryResponse);
//        Memory result = memoryService.create(memoryRequest);
//        assertThat(result).isEqualTo(memoryResponse);
//    }
//
//    @Test
//    public void createDuplicateShouldThrowAlreadyExistsException() throws Exception {
//        thrown.expect(AlreadyExistException.class);
//        when(memoryRepository.findByPartNumberIgnoreCase(memoryRequest.getPartNumber()))
//                .thenReturn(Optional.of(memoryResponse));
//        when(memoryRepository.save(memoryRequest)).thenReturn(memoryResponse);
//        Memory result = memoryService.create(memoryRequest);
//    }
//
//    @Test
//    public void update() throws Exception{
//
//        memoryRequest.setId(ID);
//        when(memoryRepository.findById(ID))
//                .thenReturn(Optional.of(memoryResponse));
//
//        when(memoryRepository.save(memoryRequest)).thenReturn(memoryResponse);
//
//        Memory result = memoryService.update(memoryRequest);
//
//    }
//
//    @Test
//    public void updateNonExistentResourceShouldThrowNotFoundException() throws Exception {
//        thrown.expect(NotFoundException.class);
//        memoryRequest.setId(ID);
//        when(memoryRepository.findById(ID))
//                .thenReturn(Optional.empty());
//
//        when(memoryRepository.save(memoryRequest)).thenReturn(memoryResponse);
//
//        memoryService.update(memoryRequest);
//    }
//
//    @Test
//    public void deleteById() {
//        memoryRequest.setId(ID);
//        when(memoryRepository.findById(memoryRequest.getId())).thenReturn(Optional.of(memoryResponse));
//        doNothing().when(memoryRepository).deleteById(memoryRequest.getId());
//        memoryService.deleteById(ID);
//    }
//
//    @Test
//    public void deleteByNonExistentIdShouldReturnNotFoundError() {
//
//        thrown.expect(NotFoundException.class);
//
//        when(memoryRepository.findById(memoryRequest.getId())).thenReturn(Optional.empty());
//
//        memoryService.deleteById(ID);
//    }
//
//    @Test
//    public void deleteSmokeTest() {
//        memoryRequest.setId(ID);
//        when(memoryRepository.findById(ID))
//                .thenReturn(Optional.of(memoryResponse));
//        memoryService.delete(memoryRequest);
//    }
//
//    @Test
//    public void deleteNonExistentResourceShouldThrowNotFoundException() {
//        thrown.expect(NotFoundException.class);
//        when(memoryRepository.findById(ID))
//                .thenReturn(Optional.empty());
//        memoryService.delete(memoryRequest);
//    }
//
//    @Test
//    public void read() {
//        when(memoryRepository.findById(ID))
//                .thenReturn(Optional.of(memoryResponse));
//        Memory memory = memoryService.read(ID);
//
//        assertThat(memory).isEqualTo(memoryResponse);
//    }
//
//    @Test
//    public void readNotExistentIdShouldReturnNotFoundException() {
//        thrown.expect(NotFoundException.class);
//        when(memoryRepository.findById(ID))
//                .thenReturn(Optional.empty());
//        Memory memory = memoryService.read(ID);
//    }
//
//    @Test
//    public void findMemoryByBrandAndModel() {
//        when(memoryRepository.findMemoryByBrandAndModelAllIgnoreCase(memoryRequest.getBrand(), memoryRequest.getModel()))
//                .thenReturn(Optional.of(memoryResponse));
//        Memory memory = memoryService.findMemoryByBrandAndModel(memoryRequest.getBrand(), memoryRequest.getModel());
//
//        assertThat(memory).isEqualTo(memoryResponse);
//    }
//
//    @Test
//    public void findByPartNumberShouldReturnResource() {
//        when(memoryRepository.findByPartNumberIgnoreCase(memoryRequest.getPartNumber())).thenReturn(Optional.of(memoryResponse));
//        Memory memory = memoryService.findByPartNumber(memoryRequest.getPartNumber());
//        assertThat(memory).isEqualTo(memoryResponse);
//    }
//
//    @Test
//    public void findByNonExistentPartNumberShouldReturnResource() {
//        thrown.expect(NotFoundException.class);
//        when(memoryRepository.findByPartNumberIgnoreCase(memoryRequest.getPartNumber())).thenReturn(Optional.empty());
//        memoryService.findByPartNumber(memoryRequest.getPartNumber());
//
//    }
//
//    @Test
//    public void findNotExistentBrandAndModelShouldReturnNotFoundException() {
//        thrown.expect(NotFoundException.class);
//        when(memoryRepository.findMemoryByBrandAndModelAllIgnoreCase(memoryRequest.getBrand(), memoryRequest.getModel()))
//                .thenReturn(Optional.empty());
//        memoryService.findMemoryByBrandAndModel(memoryRequest.getBrand(), memoryRequest.getModel());
//    }
//
//    @Test
//    public void findAll() {
//        List<Memory> memoryList = new ArrayList<>();
//        memoryList.add(new Memory("Kingston", "Saphire","AAA","DDR4",500, 4));
//        memoryList.add(new Memory("Kingston", "Saphire","AAA","DDR4",500, 4));
//        memoryList.add(new Memory("Kingston", "Saphire","AAA","DDR4",500, 4));
//        memoryList.add(new Memory("Kingston", "Saphire","AAA","DDR4",500, 4));
//        when(memoryRepository.findAll())
//                .thenReturn(memoryList);
//        List<Memory> result = memoryService.findAll();
//        assertThat(result).isEqualTo(memoryList);
//
//    }


}
