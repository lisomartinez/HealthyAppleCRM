package ar.com.healthyapple.crm_web.service.Client;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ClientProductServiceImplTest {
//    private static final Long ID = 1L;
//
//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//
//    @TestConfiguration
//    static class ServiceServiceTestContextConfiguration {
//
//        @Bean
//        public ClientService clientService(ClientRepository clientRepository, ClientInfoValidator clientInfoValidator) {
//            return new ClientServiceImpl(clientRepository, clientInfoValidator);
//        }
//    }
//
//    @Autowired
//    private ClientService clientService;
//
//
//    @MockBean
//    private ClientRepository clientRepository;
//
//    @MockBean
//    private ClientInfoValidator clientInfoValidator;
//
//    @MockBean
//    private List<Product> products;
//
//    @MockBean
//    private List<Sale> services;
//
//
//    private Client clientRequest;
//
//    private Client clientResponse;
//
//
//    @Before
//    public void setUp() {
//        clientRequest = new Client(1111123123213L, LocalDate.of(2018, 2, 1), "Juan", "Perez", "jp@gmail.com", "Lalal", products, services);
//        clientResponse = new Client(1111123123213L, LocalDate.of(2018, 2, 1), "Juan", "Perez", "jp@gmail.com", "Lalal", products, services);
//    }
//
//    @Test
//    public void create() throws AlreadyExistException {
//        when(clientRepository.findById(clientRequest.getMobile()))
//                .thenReturn(Optional.empty());
//        when(clientRepository.save(clientRequest)).thenReturn(clientResponse);
//        Client result = clientService.create(clientRequest);
//
//        assertThat(result).isEqualTo(clientResponse);
//    }
//
//    @Test
//    public void createDuplicateShouldThrowAlreadyExistsException() throws Exception {
//        thrown.expect(AlreadyExistException.class);
//        when(clientRepository.findById(clientRequest.getMobile()))
//                .thenReturn(Optional.of(clientResponse));
//        clientService.create(clientRequest);
//    }
//
//    @Test
//    public void read() {
//        when(clientRepository.findById(clientRequest.getMobile())).thenReturn(Optional.of(clientResponse));
//        Client result = clientService.read(clientRequest.getMobile());
//        assertThat(result).isEqualTo(clientResponse);
//    }
//
//    @Test
//    public void readByNonExistentIdShouldThrowNotFoundException() {
//        thrown.expect(NotFoundException.class);
//        when(clientRepository.findById(ID)).thenReturn(Optional.empty());
//        Client result = clientService.read(ID);
//    }
//
//    @Test
//    public void update() {
//        when(clientRepository.findById(clientRequest.getMobile())).thenReturn(Optional.of(clientResponse));
//        Client result = clientService.read(clientRequest.getMobile());
//        assertThat(result).isEqualTo(clientResponse);
//    }
//
//    @Test
//    public void updateNonExistentResourceThrowsNotFoundException() {
//        thrown.expect(NotFoundException.class);
//        when(clientRepository.findById(ID)).thenReturn(Optional.of(clientResponse));
//        Client result = clientService.read(clientRequest.getMobile());
//        assertThat(result).isEqualTo(clientResponse);
//    }
//
//    @Test
//    public void deleteByMobile() {
//        when(clientRepository.findById(clientRequest.getMobile())).thenReturn(Optional.of(clientResponse));
//        doNothing().when(clientRepository).deleteById(clientRequest.getMobile());
//        clientService.deleteByMobile(clientRequest.getMobile());
//    }
//
//    @Test
//    public void deleteByNonExistentIdShouldThrowNotFoundException() throws NotFoundException {
//        thrown.expect(NotFoundException.class);
//        when(clientRepository.findById(ID)).thenReturn(Optional.empty());
//        doNothing().when(clientRepository).deleteById(clientRequest.getMobile());
//        clientService.deleteByMobile(ID);
//    }
//
//    @Test
//    public void delete() {
//        when(clientRepository.findById(clientRequest.getMobile())).thenReturn(Optional.of(clientResponse));
//        doNothing().when(clientRepository).delete(clientRequest);
//        clientService.deleteByMobile(clientRequest.getMobile());
//    }
//
//    @Test
//    public void deleteByNonExistentResourceShouldThrowNotFoundException() {
//        thrown.expect(NotFoundException.class);
//        when(clientRepository.findById(ID)).thenReturn(Optional.empty());
//        doNothing().when(clientRepository).delete(clientRequest);
//        clientService.delete(clientRequest);
//    }
//
//    @Test
//    public void findByNameOrLastName() {
////        when(clientRepository.findByFirstNameContainingAndLastNameContaining(clientRequest.getFirstName().toLowerCase(), PageRequest.of(0, 1))).thenReturn(Arrays.asList(clientResponse));
////        List<Client>  clients = clientService.findByFirstNameOrLastName(clientRequest.getFirstName().toLowerCase(), 1, 1);
////        assertThat(clients).containsExactlyInAnyOrder(clientResponse);
//    }

}