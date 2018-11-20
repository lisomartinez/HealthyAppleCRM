package ar.com.healthyapple.crm_web.repository.Client;

import ar.com.healthyapple.crm_web.DatabaseLoader;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.repository.Product.*;
import lombok.extern.java.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ClientCustomRepositoryImplTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private DatabaseLoader databaseLoader;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;


    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private SpecificationRepository specificationRepository;

    @Autowired
    private ProductProfileRepository productProfileRepository;

    @Autowired
    private ComponentProfileRepository componentProfileRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ComponentTypeRepository componentTypeRepository;

    @BeforeEach
    void setUp() {
        databaseLoader = new DatabaseLoader(
                clientRepository,
                productTypeRepository,
                componentTypeRepository,
                componentRepository,
                specificationRepository,
                productProfileRepository,
                componentProfileRepository);
        databaseLoader.execute();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findClientProductsProfileDescriptionByProductType() {

        Client client = clientRepository.findById(1L).orElseThrow(NotFoundException::new);
        int quantity = client.getProducts().size();
        Map<Long, String> results = clientRepository.findClientProductsProfileDescriptionByProductType(client.getId());
        logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
         client.getProducts().stream().forEach(product -> logger.debug(product));
        logger.debug("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        results.forEach((key,value)  -> logger.debug(key.toString() + " --- " + results.get(key)));

        assertThat(results.size()).isEqualTo(quantity);
    }
}