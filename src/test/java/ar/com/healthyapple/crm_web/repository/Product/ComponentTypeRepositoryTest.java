package ar.com.healthyapple.crm_web.repository.Product;



import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Product.ComponentType;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ComponentTypeRepositoryTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private ComponentTypeRepository componentTypeRepository;

    private ComponentType componentType;

    @Before
    public void setUp() {
        componentType = new ComponentType("type1");
        componentType = componentTypeRepository.save(componentType);
    }

    @Test
    public void findByNameIgnoreCaseLowerCase() {
        Optional<ComponentType> result = componentTypeRepository.findByNameIgnoreCase("type1");
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(componentType);
    }

    @Test
    public void findByNameIgnoreCaseUpperCase() {
        Optional<ComponentType> result = componentTypeRepository.findByNameIgnoreCase("TYPE1");
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(componentType);
    }

    @Test
    public void findByNameIgnoreCaseMixedCase() {
        Optional<ComponentType> result = componentTypeRepository.findByNameIgnoreCase("tYPe1");
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(componentType);
    }

    @Test
    public void findByNameIgnoreCaseNonExistentResourceReturnEmptyOptional() {
        Optional<ComponentType> result = componentTypeRepository.findByNameIgnoreCase("NonExistent");
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void findByNameIgnoreCasewithNullStringShouldReturnEmptyOptional() {
        Optional<ComponentType> result = componentTypeRepository.findByNameIgnoreCase(null);
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void findByNameIgnoreCasewithEmptyStringShouldReturnEmptyOptional() {
        Optional<ComponentType> result = componentTypeRepository.findByNameIgnoreCase("");
        assertThat(result.isPresent()).isFalse();
    }
}
