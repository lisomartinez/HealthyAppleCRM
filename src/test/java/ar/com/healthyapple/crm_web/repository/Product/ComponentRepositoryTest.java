package ar.com.healthyapple.crm_web.repository.Product;

import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Product.Component;
import ar.com.healthyapple.crm_web.model.Product.ComponentType;
import ar.com.healthyapple.crm_web.model.Product.Specification;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ComponentRepositoryTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private ComponentTypeRepository componentTypeRepository;

    private Component component1;
    private Component component2;
    private Component component3;
    private Component component4;


    @Before
    public void setUp() {
        ComponentType componentType = new ComponentType("type1");
        componentType = componentTypeRepository.save(componentType);

        Specification specification = Mockito.mock(Specification.class);
        List<Specification> specificationList = new ArrayList<>(Arrays.asList(specification));

        component1 = new Component("description1", componentType, specificationList);
        component1 =  componentRepository.save(component1);

        component2 = new Component("description2", componentType, specificationList);
        component2 = componentRepository.save(component1);

        component3 = new Component("description3", componentType, specificationList);
        component3 = componentRepository.save(component1);

        component4 = new Component("description4", componentType, specificationList);
        component4 = componentRepository.save(component1);
    }



    @Test
    public void findByID() throws Exception {
        Component result = componentRepository.findById(component1.getId())
                .orElseThrow(NotFoundException::new);
        assertThat(result).isEqualTo(component1);
    }

    @Test
    public void findByDescriptionIgnoreCaseUpperCase() throws Exception {
        Component result = componentRepository.findByDescriptionIgnoreCase("DESCRIPTION1")
                .orElseThrow(NotFoundException::new);
        assertThat(result).isEqualTo(component1);
    }

    @Test
    public void findByDescriptionIgnoreCaseLowerCase() throws Exception {
        Component result = componentRepository.findByDescriptionIgnoreCase("description1")
                .orElseThrow(NotFoundException::new);
        assertThat(result).isEqualTo(component1);
    }

    @Test
    public void findByDescriptionIgnoreCaseMixedCase() throws Exception {
        Component result = componentRepository.findByDescriptionIgnoreCase("DEScripTION1")
                .orElseThrow(NotFoundException::new);
        assertThat(result).isEqualTo(component1);
    }

    @Test
    public void findByDescriptionIgnoreCaseNonExistentResourceShouldTrowNotFoundException() throws Exception {
        exception.expect(NotFoundException.class);
        Component result = componentRepository.findByDescriptionIgnoreCase("NonExistent")
                .orElseThrow(NotFoundException::new);
    }

}