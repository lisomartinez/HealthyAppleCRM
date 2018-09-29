package ar.com.healthyapple.crm_web.repository.Client;

import ar.com.healthyapple.crm_web.model.Client.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;


    @Before
    public void setUp() throws Exception {
        Client client = new Client(1111123123213L, LocalDate.of(2016, 2, 1), "Juan", "Perez", "jp@gmail.com", "LALALA 123");
        clientRepository.save(client);

        client = new Client(998131123213L, LocalDate.of(2016, 2, 1),"Pedro", "Salame", "ps@gmail.com", "LALALA 456");
        clientRepository.save(client);

        client = new Client(242323123213L,LocalDate.of(2017, 2, 1), "Jose", "SinApellido", "js@gmail.com", "LALALA 7856");
        clientRepository.save(client);

        client = new Client(4123123L,LocalDate.of(2017, 4, 1), "Ramiro", "Novaro", "rn@gmail.com", "LALALA 12313");
        clientRepository.save(client);

        client = new Client(423121233L,LocalDate.of(2018, 2, 1), "Joaquin", "Martinez", "jm@gmail.com", "LALALA 97834");
        clientRepository.save(client);

        client = new Client(1223213L, LocalDate.of(2011, 2, 1), "Salame", "Lopez", "sl@gmail.com", "LALALA 6345");
        clientRepository.save(client);

        client = new Client(312123213L, LocalDate.of(2015, 2, 1),"Juan", "Alfonso", "ja@gmail.com", "LALALA 47567");
        clientRepository.save(client);
    }

    @Test
    public void findByFirstNameContainingAndLastNameContaining() {
        List<Client> clients = clientRepository.findByFirstNameContainingAndLastNameContaining("Salame".toLowerCase(), PageRequest.of(0, 2)).getContent();
        assertThat(clients).containsExactlyInAnyOrder(new Client(1223213L, LocalDate.of(2011, 2, 1), "Salame", "Lopez", "sl@gmail.com", "LALALA 6345"),
                new Client(998131123213L, LocalDate.of(2016, 2, 1), "Pedro", "Salame", "ps@gmail.com", "LALALA 456"));
    }

    @Test
    public void findAll() {
        List<Client> clients = clientRepository.findAll();
        assertThat(clients.size()).isEqualTo(7);
    }
}