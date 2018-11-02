package ar.com.healthyapple.crm_web.service.Sale;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Product.Product;
import ar.com.healthyapple.crm_web.model.Sale.ProductService;
import ar.com.healthyapple.crm_web.repository.Service.ServiceRepository;
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

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class ServiceProductServiceImplTest {

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

    private ProductService productServiceRequest;

    private ProductService productServiceResponse;


    @Before
    public void setUp() {
        productServiceRequest = new ProductService("ServiceTest", "This is a service test", product, BigDecimal.valueOf(3000));
        productServiceResponse = new ProductService("ServiceTest", "This is a service test", product, BigDecimal.valueOf(3000));
        productServiceResponse.setId(ID);
    }

    @Test
    public void create() throws AlreadyExistException {
        when(serviceRepository.findByProductAndAndName(productServiceRequest.getProduct(), productServiceRequest.getName()))
                .thenReturn(Optional.empty());
        when(serviceRepository.save(productServiceRequest)).thenReturn(productServiceResponse);
        ProductService result = serviceService.create(productServiceRequest);

        assertThat(result).isEqualTo(productServiceResponse);
    }

    @Test
    public void createDuplicateShouldThrowAlreadyExistsException() {
        thrown.expect(AlreadyExistException.class);
        when(serviceRepository.findByProductAndAndName(productServiceRequest.getProduct(), productServiceRequest.getName()))
                .thenReturn(Optional.of(productServiceResponse));
        serviceService.create(productServiceRequest);
    }

    @Test
    public void read() {
        productServiceRequest.setId(1L);
        when(serviceRepository.findById(productServiceRequest.getId())).thenReturn(Optional.of(productServiceResponse));
        ProductService result = serviceService.read(ID);
        assertThat(result).isEqualTo(productServiceResponse);
    }

    @Test
    public void readByNonExistentIdShouldThrowNotFoundException() {
        thrown.expect(NotFoundException.class);
        when(serviceRepository.findById(productServiceRequest.getId())).thenReturn(Optional.empty());
        ProductService result = serviceService.read(ID);
    }

    @Test
    public void update() {
        productServiceRequest.setId(1L);
        when(serviceRepository.findById(1L)).thenReturn(Optional.of(productServiceResponse));
        ProductService result = serviceService.read(productServiceRequest.getId());
        assertThat(result).isEqualTo(productServiceResponse);
    }

    @Test
    public void deleteById() {
        productServiceRequest.setId(1L);
        when(serviceRepository.findById(productServiceRequest.getId())).thenReturn(Optional.of(productServiceResponse));
        doNothing().when(serviceRepository).deleteById(ID);
        serviceService.deleteById(ID);
    }

    @Test
    public void deleteByNonExistentIdShouldThrowNotFoundException() throws NotFoundException {
        thrown.expect(NotFoundException.class);
        productServiceRequest.setId(1L);
        when(serviceRepository.findById(productServiceRequest.getId())).thenReturn(Optional.empty());
        doNothing().when(serviceRepository).deleteById(ID);
        serviceService.deleteById(ID);
    }

    @Test
    public void delete() {
        productServiceRequest.setId(1L);
        when(serviceRepository.findById(productServiceRequest.getId())).thenReturn(Optional.of(productServiceResponse));
        doNothing().when(serviceRepository).delete(productServiceRequest);
        serviceService.deleteById(ID);
    }

    @Test
    public void deleteByNonExistentResourceShouldThrowNotFoundException() {
        thrown.expect(NotFoundException.class);
        when(serviceRepository.findById(productServiceRequest.getId())).thenReturn(Optional.empty());
        doNothing().when(serviceRepository).delete(productServiceRequest);
        serviceService.delete(productServiceRequest);
    }
}