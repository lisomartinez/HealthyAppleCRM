package ar.com.healthyapple.crm_web.repository.Product;

import ar.com.healthyapple.crm_web.model.Product.ComponentProfile;
import ar.com.healthyapple.crm_web.model.Product.ProductProfile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductProfileRepositoryTest {

    @Autowired
    private ProductProfileRepository productProfileRepository;

    private ProductProfile productProfile1;
    private ProductProfile productProfile2;
    private ProductProfile productProfile3;
    private ProductProfile productProfile4;

    @Before
    public  void setUp() {
        ComponentProfile componentProfile = Mockito.mock(ComponentProfile.class);

        productProfile1 = new ProductProfile("name1", "description1", Arrays.asList(componentProfile));
        productProfile1 = productProfileRepository.save(productProfile1);

        productProfile2 = new ProductProfile("name2", "description2", Arrays.asList(componentProfile));
        productProfile2 = productProfileRepository.save(productProfile2);

        productProfile3 = new ProductProfile("name3", "description3", Arrays.asList(componentProfile));
        productProfile3 = productProfileRepository.save(productProfile3);

        productProfile4 = new ProductProfile("name4", "description4", Arrays.asList(componentProfile));
        productProfile4 = productProfileRepository.save(productProfile4);

    }

    @Test
    public void findByTypeIgnoreCaseLowerCase() {
        Optional<ProductProfile> result = productProfileRepository.findByTypeIgnoreCase("name1");
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(productProfile1);
    }

    @Test
    public void findByTypeIgnoreCaseUpperCase() {
        Optional<ProductProfile> result = productProfileRepository.findByTypeIgnoreCase("NAME1");
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(productProfile1);
    }

    @Test
    public void findByTypeIgnoreCaseMixedCase() {
        Optional<ProductProfile> result = productProfileRepository.findByTypeIgnoreCase("nAMe1");
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(productProfile1);
    }

    @Test
    public void findByTypeIgnoreCaseNonExistentResource() {
        Optional<ProductProfile> result = productProfileRepository.findByTypeIgnoreCase("NotFound");
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void findByTypeIgnoreCaseNullString() {
        Optional<ProductProfile> result = productProfileRepository.findByTypeIgnoreCase(null);
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void findByTypeIgnoreCaseEmptyString() {
        Optional<ProductProfile> result = productProfileRepository.findByTypeIgnoreCase("");
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void getProfilesNames() {
        Map<Long, String> profiles = new HashMap<>();
        profiles.put(productProfile1.getId(), productProfile1.getDescription());
        profiles.put(productProfile2.getId(), productProfile2.getDescription());
        profiles.put(productProfile3.getId(), productProfile3.getDescription());
        profiles.put(productProfile4.getId(), productProfile4.getDescription());

        Map<Long, String> result = productProfileRepository.getProfilesNames();
        assertThat(result).isEqualTo(profiles);
    }
}