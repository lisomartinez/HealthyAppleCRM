package ar.com.healthyapple.crm_web.service.Sale;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Computer.PcCase;
import ar.com.healthyapple.crm_web.model.Product;
import ar.com.healthyapple.crm_web.model.Service;
import ar.com.healthyapple.crm_web.repository.ServiceRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class ServiceServiceImplTest {

    private static final Long ID = 1L;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @TestConfiguration
    static class ServiceServiceTestContextConfiguration {

        @Bean
        public ServiceService serviceService(ServiceRepository serviceRepository) {
            return new ServiceServiceImpl(serviceRepository);
        }
    }

    @Autowired
    private ServiceService serviceService;

    @MockBean
    private ServiceRepository serviceRepository;

    @MockBean
    private Product product;

    private Service serviceRequest;

    private Service serviceResponse;


    @Before
    public void setUp() throws Exception {
        serviceRequest = new Service("ServiceTest", "This is a service test", product, BigDecimal.valueOf(3000));
        serviceResponse = new Service("ServiceTest", "This is a service test", product, BigDecimal.valueOf(3000));
        serviceResponse.setId(ID);
    }

    @Test
    public void create() throws AlreadyExistException {
        when(serviceRepository.findByProductAndAndName(serviceRequest.getProduct(), serviceRequest.getName()))
                .thenReturn(Optional.empty());
        when(serviceRepository.save(serviceRequest)).thenReturn(serviceResponse);
        Service result = serviceService.create(serviceRequest);

        assertThat(result).isEqualTo(serviceResponse);
    }

    @Test
    public void createDuplicateShouldThrowAlreadyExistsException() throws Exception {
        thrown.expect(AlreadyExistException.class);
        when(serviceRepository.findByProductAndAndName(serviceRequest.getProduct(), serviceRequest.getName()))
                .thenReturn(Optional.of(serviceResponse));
        serviceService.create(serviceRequest);
    }

    @Test
    public void read() {
        serviceRequest.setId(1L);
        when(serviceRepository.findById(serviceRequest.getId())).thenReturn(Optional.of(serviceResponse));
        Service result = serviceService.read(ID);
        assertThat(result).isEqualTo(serviceResponse);
    }

    @Test
    public void readByNonExistentIdShouldThrowNotFoundException() throws Exception {
        thrown.expect(NotFoundException.class);
        when(serviceRepository.findById(serviceRequest.getId())).thenReturn(Optional.empty());
        Service result = serviceService.read(ID);
    }

    @Test
    public void update() {
        serviceRequest.setId(1L);
        when(serviceRepository.findById(1L)).thenReturn(Optional.of(serviceResponse));
        Service result = serviceService.read(serviceRequest.getId());
        assertThat(result).isEqualTo(serviceResponse);
    }

    @Test
    public void deleteById() {
        serviceRequest.setId(1L);
        when(serviceRepository.findById(serviceRequest.getId())).thenReturn(Optional.of(serviceResponse));
        doNothing().when(serviceRepository).deleteById(ID);
        serviceService.deleteById(ID);
    }

    @Test
    public void deleteByNonExistentIdShouldThrowNotFoundException() throws NotFoundException {
        thrown.expect(NotFoundException.class);
        serviceRequest.setId(1L);
        when(serviceRepository.findById(serviceRequest.getId())).thenReturn(Optional.empty());
        doNothing().when(serviceRepository).deleteById(ID);
        serviceService.deleteById(ID);
    }

    @Test
    public void delete() {
        serviceRequest.setId(1L);
        when(serviceRepository.findById(serviceRequest.getId())).thenReturn(Optional.of(serviceResponse));
        doNothing().when(serviceRepository).delete(serviceRequest);
        serviceService.deleteById(ID);
    }

    @Test
    public void deleteByNonExistentResourceShouldThrowNotFoundException() throws Exception {
        thrown.expect(NotFoundException.class);
        when(serviceRepository.findById(serviceRequest.getId())).thenReturn(Optional.empty());
        doNothing().when(serviceRepository).delete(serviceRequest);
        serviceService.delete(serviceRequest);
    }
}