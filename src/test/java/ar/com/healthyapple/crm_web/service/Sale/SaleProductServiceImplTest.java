package ar.com.healthyapple.crm_web.service.Sale;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class SaleProductServiceImplTest {

//    private static final Long ID = 1L;
//
//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//
//    @TestConfiguration
//    static class SaleServiceTestContextConfiguration {
//
//        @Bean
//        public SaleService saleService(SaleRepository saleRepository) {
//            return new SaleServiceImpl(saleRepository);
//        }
//    }
//
//    @Autowired
//    private SaleService saleService;
//
//    @MockBean
//    private SaleRepository saleRepository;
//
//    @MockBean
//    private Product product;
//
//    @MockBean
//    private SaleState saleState;
//
//    @MockBean
//    private Client clientId;
//
//    private SaleItem productServiceSale;
//
//    private Sale saleRequest;
//
//    private Sale saleResponse;
//
//
//    @Before
//    public void setUp() {
//
//
//        productServiceSale =  new SaleItem("ServiceTest", "This is a productServiceSale test",  product, BigDecimal.valueOf(1000));
//        saleRequest = new SaleBuilder()
//                .setName("serviceTest")
//                .setDescription("This is a productServiceSale test")
//                .setState(saleState)
//                .setClientId(clientId)
//                .setOriginDate( LocalDate.of(2018, 3, 10))
//                .setDueDate( LocalDate.of(2018, 9, 10))
//                .setFinalPrice(BigDecimal.valueOf(5000))
//                .setServiceList(Arrays.asList(productServiceSale))
//                .build();
//        saleResponse = new SaleBuilder()
//                .setId(ID)
//                .setName("serviceTest")
//                .setDescription("This is a productServiceSale test")
//                .setState(saleState)
//                .setClientId(clientId)
//                .setOriginDate( LocalDate.of(2018, 3, 10))
//                .setDueDate( LocalDate.of(2018, 9, 10))
//                .setFinalPrice(BigDecimal.valueOf(5000))
//                .setServiceList(Arrays.asList(productServiceSale))
//                .build();
//    }
//
//    @Test
//    public void create() throws AlreadyExistException {
//        assertThat(saleRequest).isNotNull();
//        assertThat(saleResponse).isNotNull();
//        assertThat(saleRepository).isNotNull();
//        when(saleRepository.save(saleRequest)).thenReturn(saleResponse);
//        Sale result = saleService.create(saleRequest);
//
//        assertThat(result).isEqualTo(saleResponse);
//    }
//
//    @Test
//    public void read() {
//        saleRequest.setId(1L);
//        when(saleRepository.findById(saleRequest.getId())).thenReturn(Optional.of(saleResponse));
//        Sale result = saleService.read(ID);
//
//        assertThat(result).isEqualTo(saleResponse);
//    }
//
//    @Test
//    public void readByNonExistentIdShouldThrowNotFoundException() {
//        thrown.expect(NotFoundException.class);
//        when(saleRepository.findById(saleRequest.getId())).thenReturn(Optional.empty());
//        Sale result = saleService.read(ID);
//    }
//
//    @Test
//    public void update() {
//        saleRequest.setId(1L);
//        when(saleRepository.findById(1L)).thenReturn(Optional.of(saleResponse));
//        Sale result = saleService.read(saleRequest.getId());
//        assertThat(result).isEqualTo(saleResponse);
//    }
//
//    @Test
//    public void deleteById() {
//        saleRequest.setId(1L);
//        when(saleRepository.findById(saleRequest.getId())).thenReturn(Optional.of(saleResponse));
//        doNothing().when(saleRepository).deleteById(ID);
//        saleService.deleteById(ID);
//    }
//
//    @Test
//    public void deleteByNonExistentIdShouldThrowNotFoundException() throws NotFoundException {
//        thrown.expect(NotFoundException.class);
//        saleRequest.setId(1L);
//        when(saleRepository.findById(saleRequest.getId())).thenReturn(Optional.empty());
//        doNothing().when(saleRepository).deleteById(ID);
//        saleService.deleteById(ID);
//    }
//
//    @Test
//    public void delete() {
//        saleRequest.setId(1L);
//        when(saleRepository.findById(saleRequest.getId())).thenReturn(Optional.of(saleResponse));
//        doNothing().when(saleRepository).delete(saleRequest);
//        saleService.deleteById(ID);
//    }
//
//    @Test
//    public void deleteByNonExistentResourceShouldThrowNotFoundException() {
//        thrown.expect(NotFoundException.class);
//        when(saleRepository.findById(saleRequest.getId())).thenReturn(Optional.empty());
//        doNothing().when(saleRepository).delete(saleRequest);
//        saleService.delete(saleRequest);
//    }
}