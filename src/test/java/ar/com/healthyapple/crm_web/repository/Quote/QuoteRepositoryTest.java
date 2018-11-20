package ar.com.healthyapple.crm_web.repository.Quote;

import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Product.Product;
import ar.com.healthyapple.crm_web.model.Product.ProductState;
import ar.com.healthyapple.crm_web.model.Product.StateBasedProduct;
import ar.com.healthyapple.crm_web.model.Quote.QQuote;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteItem;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;
import ar.com.healthyapple.crm_web.repository.Client.ClientRepository;
import ar.com.healthyapple.crm_web.repository.Product.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
//@EnableTransactionManagement
public class QuoteRepositoryTest {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    private final static Long NUMBER = 1L;
    private final static Integer VERSION = 1;
    private final static String DESCRIPTION = "Quote description";
    private final static LocalDateTime CREATED = LocalDateTime.now();
    private final static BigDecimal COST = BigDecimal.valueOf(1000);
    private final static BigDecimal PRICE = BigDecimal.valueOf(500);

    private Quote quote1;
    private Quote quote2;
    private Quote quote3;
    private Quote quote4;
    private Quote quote5;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getNewestQuotes() {

//        Product product = new Product();
//        product = productRepository.save(product);
        List<StateBasedProduct> productMap = new ArrayList<>(Arrays.asList(new StateBasedProduct()));
        Client client = new Client();
        client = clientRepository.save(client);


        Quote first = new Quote(1L,
                1,
                "first",
                QuoteState.NEW,
                LocalDateTime.of(2001,9, 11, 22,00),
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(1),
                client,
                productMap,
                new ArrayList<QuoteItem>()
        );

        quoteRepository.save(first);

        Quote second = new Quote(2L,
                2,
                "second",
                QuoteState.NEW,
                LocalDateTime.of(2001,9, 12, 22,00),
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(2),
                client,
                productMap,
            new ArrayList<QuoteItem>()

        );
        quoteRepository.save(second);


        Quote third = new Quote(3L,
                3,
                "second",
                QuoteState.NEW,
                LocalDateTime.of(2002,9, 12, 22,00),
                BigDecimal.valueOf(3),
                BigDecimal.valueOf(3),
                client,
                productMap,
                new ArrayList<QuoteItem>()

        );
        quoteRepository.save(third);

        Quote fourth = new Quote(3L,
                4,
                "second",
                QuoteState.NEW,
                LocalDateTime.of(2003,10, 12, 22,00),
                BigDecimal.valueOf(4),
                BigDecimal.valueOf(4),
                client,
                productMap,
                new ArrayList<QuoteItem>()

        );
        quoteRepository.save(fourth);

        List<Quote> result = quoteRepository.getNewestQuotes(1);
        assertThat(result).containsExactlyInAnyOrder(fourth);
    }
}