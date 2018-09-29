package ar.com.healthyapple.crm_web.service.Sale;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Product;
import ar.com.healthyapple.crm_web.model.Sale.Sale;
import ar.com.healthyapple.crm_web.model.Sale.SaleState;
import ar.com.healthyapple.crm_web.model.Sale.SaleStateEnum;
import ar.com.healthyapple.crm_web.model.Service;
import ar.com.healthyapple.crm_web.repository.SaleRepository;
import ar.com.healthyapple.crm_web.repository.Service.SaleBuilder;
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
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class SaleServiceImplTest {

    private static final Long ID = 1L;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @TestConfiguration
    static class SaleServiceTestContextConfiguration {

        @Bean
        public SaleService saleService(SaleRepository saleRepository) {
            return new SaleServiceImpl(saleRepository);
        }
    }

    @Autowired
    private SaleService saleService;

    @MockBean
    private SaleRepository saleRepository;

    @MockBean
    private Product product;

    @MockBean
    private SaleState saleState;

    @MockBean
    private Client client;

    private Service service;

    private Sale saleRequest;

    private Sale saleResponse;


    @Before
    public void setUp() throws Exception {


        service =  new Service("ServiceTest", "This is a service test",  product, BigDecimal.valueOf(1000));
        saleRequest = new SaleBuilder()
                .setTag("serviceTest")
                .setDescription("This is a service test")
                .setState(saleState)
                .setClient(client)
                .setOriginDate( LocalDate.of(2018, 3, 10))
                .setDueDate( LocalDate.of(2018, 9, 10))
                .setTotalCost(BigDecimal.valueOf(3000))
                .setFinalPrice(BigDecimal.valueOf(5000))
                .setServiceList(Arrays.asList(service))
                .build();
        saleResponse = new SaleBuilder()
                .setId(ID)
                .setTag("serviceTest")
                .setDescription("This is a service test")
                .setState(saleState)
                .setClient(client)
                .setOriginDate( LocalDate.of(2018, 3, 10))
                .setDueDate( LocalDate.of(2018, 9, 10))
                .setTotalCost(BigDecimal.valueOf(3000))
                .setFinalPrice(BigDecimal.valueOf(5000))
                .setServiceList(Arrays.asList(service))
                .build();
    }

    @Test
    public void create() throws AlreadyExistException {
        assertThat(saleRequest).isNotNull();
        assertThat(saleResponse).isNotNull();
        assertThat(saleRepository).isNotNull();
        when(saleRepository.save(saleRequest)).thenReturn(saleResponse);
        Sale result = saleService.create(saleRequest);

        assertThat(result).isEqualTo(saleResponse);
    }

    @Test
    public void read() {
        saleRequest.setId(1L);
        when(saleRepository.findById(saleRequest.getId())).thenReturn(Optional.of(saleResponse));
        Sale result = saleService.read(ID);

        assertThat(result).isEqualTo(saleResponse);
    }

    @Test
    public void readByNonExistentIdShouldThrowNotFoundException() throws Exception {
        thrown.expect(NotFoundException.class);
        when(saleRepository.findById(saleRequest.getId())).thenReturn(Optional.empty());
        Sale result = saleService.read(ID);
    }

    @Test
    public void update() {
        saleRequest.setId(1L);
        when(saleRepository.findById(1L)).thenReturn(Optional.of(saleResponse));
        Sale result = saleService.read(saleRequest.getId());
        assertThat(result).isEqualTo(saleResponse);
    }

    @Test
    public void deleteById() {
        saleRequest.setId(1L);
        when(saleRepository.findById(saleRequest.getId())).thenReturn(Optional.of(saleResponse));
        doNothing().when(saleRepository).deleteById(ID);
        saleService.deleteById(ID);
    }

    @Test
    public void deleteByNonExistentIdShouldThrowNotFoundException() throws NotFoundException {
        thrown.expect(NotFoundException.class);
        saleRequest.setId(1L);
        when(saleRepository.findById(saleRequest.getId())).thenReturn(Optional.empty());
        doNothing().when(saleRepository).deleteById(ID);
        saleService.deleteById(ID);
    }

    @Test
    public void delete() {
        saleRequest.setId(1L);
        when(saleRepository.findById(saleRequest.getId())).thenReturn(Optional.of(saleResponse));
        doNothing().when(saleRepository).delete(saleRequest);
        saleService.deleteById(ID);
    }

    @Test
    public void deleteByNonExistentResourceShouldThrowNotFoundException() throws Exception {
        thrown.expect(NotFoundException.class);
        when(saleRepository.findById(saleRequest.getId())).thenReturn(Optional.empty());
        doNothing().when(saleRepository).delete(saleRequest);
        saleService.delete(saleRequest);
    }
}