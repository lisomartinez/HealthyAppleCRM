package ar.com.healthyapple.crm_web.repository.Product;


import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Product.ProductType;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ProductTypeRepositoryTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private ProductTypeRepository productTypeRepository;

    private ProductType productType;

    @Before
    public void setUp() {
        productType = new ProductType("type1");
        productType = productTypeRepository.save(productType);
    }

    @Test
    public void findByNameIgnoreCaseLowerCase()  {
        Optional<ProductType> result = productTypeRepository.findByNameIgnoreCase("type1");

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(productType);
    }

    @Test
    public void findByNameIgnoreCaseUpperCase()  {
        Optional<ProductType> result = productTypeRepository.findByNameIgnoreCase("TYPE1");

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(productType);
    }

    @Test
    public void findByNameIgnoreCaseMixedCase()  {
        Optional<ProductType> result = productTypeRepository.findByNameIgnoreCase("tYPe1");

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(productType);
    }

    @Test
    public void findByNameIgnoreCaseNonExistentResourceReturnEmptyOptional()  {
        Optional<ProductType> result = productTypeRepository.findByNameIgnoreCase("NotFound");

        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void findByNameIgnoreCaseWithNullStringReturnEmptyOptional()  {
        Optional<ProductType> result = productTypeRepository.findByNameIgnoreCase(null);

        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void findByNameIgnoreCaseWithEmptyStringReturnEmptyOptional()  {
        Optional<ProductType> result = productTypeRepository.findByNameIgnoreCase("");

        assertThat(result.isPresent()).isFalse();
    }
}