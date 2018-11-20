package ar.com.healthyapple.crm_web.service.Sale;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
public class ServiceProductServiceImplTest {
//
//    private static final Long ID = 1L;
//
//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//
//    @TestConfiguration
//    static class ServiceServiceTestContextConfiguration {
//
//        @Bean
//        public ServiceService serviceService(ServiceRepository serviceRepository) {
//            return new ServiceServiceImpl(serviceRepository);
//        }
//    }
//
//    @Autowired
//    private ServiceService serviceService;
//
//    @MockBean
//    private ServiceRepository serviceRepository;
//
//    @MockBean
//    private Product product;
//
//    private SaleItem productServiceSaleRequest;
//
//    private SaleItem productServiceSaleResponse;
//
//
//    @Before
//    public void setUp() {
//        productServiceSaleRequest = new SaleItem("ServiceTest", "This is a service test", product, BigDecimal.valueOf(3000));
//        productServiceSaleResponse = new SaleItem("ServiceTest", "This is a service test", product, BigDecimal.valueOf(3000));
//        productServiceSaleResponse.setId(ID);
//    }
//
//    @Test
//    public void create() throws AlreadyExistException {
//        when(serviceRepository.findByProductAndAndName(productServiceSaleRequest.getProduct(), productServiceSaleRequest.getName()))
//                .thenReturn(Optional.empty());
//        when(serviceRepository.save(productServiceSaleRequest)).thenReturn(productServiceSaleResponse);
//        SaleItem result = serviceService.create(productServiceSaleRequest);
//
//        assertThat(result).isEqualTo(productServiceSaleResponse);
//    }
//
//    @Test
//    public void createDuplicateShouldThrowAlreadyExistsException() {
//        thrown.expect(AlreadyExistException.class);
//        when(serviceRepository.findByProductAndAndName(productServiceSaleRequest.getProduct(), productServiceSaleRequest.getName()))
//                .thenReturn(Optional.of(productServiceSaleResponse));
//        serviceService.create(productServiceSaleRequest);
//    }
//
//    @Test
//    public void read() {
//        productServiceSaleRequest.setId(1L);
//        when(serviceRepository.findById(productServiceSaleRequest.getId())).thenReturn(Optional.of(productServiceSaleResponse));
//        SaleItem result = serviceService.read(ID);
//        assertThat(result).isEqualTo(productServiceSaleResponse);
//    }
//
//    @Test
//    public void readByNonExistentIdShouldThrowNotFoundException() {
//        thrown.expect(NotFoundException.class);
//        when(serviceRepository.findById(productServiceSaleRequest.getId())).thenReturn(Optional.empty());
//        SaleItem result = serviceService.read(ID);
//    }
//
//    @Test
//    public void update() {
//        productServiceSaleRequest.setId(1L);
//        when(serviceRepository.findById(1L)).thenReturn(Optional.of(productServiceSaleResponse));
//        SaleItem result = serviceService.read(productServiceSaleRequest.getId());
//        assertThat(result).isEqualTo(productServiceSaleResponse);
//    }
//
//    @Test
//    public void deleteById() {
//        productServiceSaleRequest.setId(1L);
//        when(serviceRepository.findById(productServiceSaleRequest.getId())).thenReturn(Optional.of(productServiceSaleResponse));
//        doNothing().when(serviceRepository).deleteById(ID);
//        serviceService.deleteById(ID);
//    }
//
//    @Test
//    public void deleteByNonExistentIdShouldThrowNotFoundException() throws NotFoundException {
//        thrown.expect(NotFoundException.class);
//        productServiceSaleRequest.setId(1L);
//        when(serviceRepository.findById(productServiceSaleRequest.getId())).thenReturn(Optional.empty());
//        doNothing().when(serviceRepository).deleteById(ID);
//        serviceService.deleteById(ID);
//    }
//
//    @Test
//    public void delete() {
//        productServiceSaleRequest.setId(1L);
//        when(serviceRepository.findById(productServiceSaleRequest.getId())).thenReturn(Optional.of(productServiceSaleResponse));
//        doNothing().when(serviceRepository).delete(productServiceSaleRequest);
//        serviceService.deleteById(ID);
//    }
//
//    @Test
//    public void deleteByNonExistentResourceShouldThrowNotFoundException() {
//        thrown.expect(NotFoundException.class);
//        when(serviceRepository.findById(productServiceSaleRequest.getId())).thenReturn(Optional.empty());
//        doNothing().when(serviceRepository).delete(productServiceSaleRequest);
//        serviceService.delete(productServiceSaleRequest);
//    }
}