package ar.com.healthyapple.crm_web.repository.Client;

import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Product.Product;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ClientRepositoryTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private ClientRepository clientRepository;

    private Client client1;

    private Client client2;

    private Client client3;

    private Client client4;

    private Client client5;

    private Client client6;

    private Client client7;

    private Long id1;
    private Long id2;
    private Long id3;
    private Long id4;
    private Long id5;
    private Long id6;
    private Long id7;

    @Before
    public void setUp() {
        client1 = new Client(1111123123213L, LocalDate.of(2016, 2, 1), "Juan", "Perez", "jp@gmail.com", "LALALA 123");
        client1 =  clientRepository.save(client1);
        id1 = client1.getId();

        client2 = new Client(998131123213L, LocalDate.of(2016, 2, 1), "Pedro", "Salame", "ps@gmail.com", "LALALA 456");
        client2 =clientRepository.save(client2);
        id2 = client2.getId();

        client3 = new Client(242323123213L, LocalDate.of(2017, 2, 1), "Jose", "SinApellido", "js@gmail.com", "LALALA 7856");
        client3 =clientRepository.save(client3);
        id3 = client3.getId();

        client4 = new Client(4123123L, LocalDate.of(2017, 4, 1), "Ramiro", "Novaro", "rn@gmail.com", "LALALA 12313");
        client4 = clientRepository.save(client4);
        id4 = client4.getId();

        client5 = new Client(423121233L, LocalDate.of(2018, 2, 1), "Joaquin", "Martinez", "jm@gmail.com", "LALALA 97834");
        client5 = clientRepository.save(client5);
        id5 = client5.getId();

        client6 = new Client(1223213L, LocalDate.of(2011, 2, 1), "Salame", "Lopez", "sl@gmail.com", "LALALA 6345");
        client6 =  clientRepository.save(client6);
        id6 = client6.getId();

        client7 = new Client(312123213L, LocalDate.of(2015, 2, 1), "Juan", "Alfonso", "ja@gmail.com", "LALALA 47567");
        client7 = clientRepository.save(client7);
        id7 = client7.getId();
    }

    @Test
    public void findByFirstNameContainingAndLastNameContaining() {
        List<Client> clients = clientRepository.findByFirstNameContainingAndLastNameContaining("Salame".toLowerCase(), PageRequest.of(0, 2)).getContent();
        Client result1 = new Client(1223213L, LocalDate.of(2011, 2, 1), "Salame", "Lopez", "sl@gmail.com", "LALALA 6345");
        result1.setId(id6);
        Client result2 = new Client(998131123213L, LocalDate.of(2016, 2, 1), "Pedro", "Salame", "ps@gmail.com", "LALALA 456");
        result2.setId(id2);
        assertThat(clients).containsExactlyInAnyOrder(
                result1,
                result2
                );
    }

    @Test
    public void findAll() {
        List<Client> clients = clientRepository.findAll();
        assertThat(clients.size()).isEqualTo(7);
    }

    @Test
    public void updateMobileShouldReturnUpdatedClient() throws Exception {
        Client updateClient = clientRepository.findById(id1).orElseThrow(Exception::new);
        updateClient.setMobile(1L);
        Client result = clientRepository.save(updateClient);
        assertThat(result).isEqualTo(updateClient);
    }

    @Test
    public void updateFirstNameShouldReturnUpdatedClient() throws Exception {
        Client updateClient = clientRepository.findById(id2).orElseThrow(Exception::new);
        updateClient.setFirstName("newFirstName");
        Client result = clientRepository.save(updateClient);
        assertThat(result).isEqualTo(updateClient);
    }

    @Test
    public void updateLastNameShouldReturnUpdatedClient() throws Exception {
        Client updateClient = clientRepository.findById(id3).orElseThrow(Exception::new);
        updateClient.setLastName("newFirstName");
        Client result = clientRepository.save(updateClient);
        assertThat(result).isEqualTo(updateClient);
    }

    @Test
    public void updateEmailShouldReturnUpdatedClient() throws Exception {
        Client updateClient = clientRepository.findById(id4).orElseThrow(Exception::new);
        updateClient.setEmail("newEmail@gmail.com");
        Client result = clientRepository.save(updateClient);
        assertThat(result).isEqualTo(updateClient);
    }

    @Test
    public void updateAddressShouldReturnUpdatedClient() throws Exception {
        Client updateClient = clientRepository.findById(id5).orElseThrow(Exception::new);
        updateClient.setAddress("newAddress");
        Client result = clientRepository.save(updateClient);
        assertThat(result).isEqualTo(updateClient);
    }

    @Test
    public void updateStartDateShouldReturnUpdatedClient() throws Exception {
        Client updateClient = clientRepository.findById(id6).orElseThrow(Exception::new);
        updateClient.setStartDate(LocalDate.of(2010, 3, 1));
        Client result = clientRepository.save(updateClient);
        assertThat(result).isEqualTo(updateClient);
    }

    @Test
    public void updateProductShouldReturnUpdatedClient() throws Exception{
        Client updateClient = clientRepository.findById(id6).orElseThrow(Exception::new);
        Product product = Mockito.mock(Product.class);
        List<Product> productList = new ArrayList<>(Arrays.asList(product));
        updateClient.setProducts(productList);
        Client result = clientRepository.save(updateClient);
        assertThat(result).isEqualTo(updateClient);
    }

    @Test
    @Ignore
    public void findClientProductsProfileDescriptionByProductType() throws Exception{

    }

}