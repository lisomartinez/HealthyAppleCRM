package ar.com.healthyapple.crm_web.repository.Product;


import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Product.ComponentProfile;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ComponentProfileRepositoryTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private ComponentProfileRepository componentProfileRepository;

    private ComponentProfile componentProfile1;
    private ComponentProfile componentProfile2;
    private ComponentProfile componentProfile3;
    private ComponentProfile componentProfile4;
    private ComponentProfile componentProfile5;

    @Before
    public void setUp() {
        Map<String, String> specifications = new HashMap<>();
        componentProfile1 = new ComponentProfile("type_of_component", "description_1", Boolean.FALSE, specifications);
        componentProfile1 = componentProfileRepository.save(componentProfile1);

        componentProfile2 = new ComponentProfile("type_of_component", "description_2", Boolean.FALSE, specifications);
        componentProfile2 = componentProfileRepository.save(componentProfile1);

        componentProfile3 = new ComponentProfile("type_of_component", "description_3", Boolean.FALSE, specifications);
        componentProfile3 = componentProfileRepository.save(componentProfile1);

        componentProfile4 = new ComponentProfile("type_of_component", "description_4", Boolean.FALSE, specifications);
        componentProfile4 = componentProfileRepository.save(componentProfile1);

        componentProfile5 = new ComponentProfile("type_of_component", "description_5", Boolean.FALSE, specifications);
        componentProfile5 = componentProfileRepository.save(componentProfile1);

    }

    @Test
    public void findByTypeIgnoreCaseLowerCase() {
        Optional<ComponentProfile> result = componentProfileRepository.findByTypeIgnoreCase("type_of_component");

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(componentProfile1);
    }

    @Test
    public void findByTypeIgnoreCaseUpperCase() {
        Optional<ComponentProfile> result  = componentProfileRepository.findByTypeIgnoreCase("TYPE_OF_COMPONENT");

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(componentProfile1);
    }

    @Test
    public void findByTypeIgnoreCaseMixedCase() {
        Optional<ComponentProfile> result  = componentProfileRepository.findByTypeIgnoreCase("type_OF_component");
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(componentProfile1);
    }

    @Test
    public void findByTypeIgnoreCaseNonExistentShouldReturnEmptyOptional() {
        Optional<ComponentProfile> result  = componentProfileRepository.findByTypeIgnoreCase("NotFound");

        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void findByTypeIgnoreCaseWithNull() throws Exception {
        String s = null;
        Optional<ComponentProfile> result  =componentProfileRepository.findByTypeIgnoreCase(s);
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void findByTypeIgnoreCaseWithEmptyString() throws Exception {
        String s = "";
        Optional<ComponentProfile> result  =componentProfileRepository.findByTypeIgnoreCase(s);
        assertThat(result.isPresent()).isFalse();
    }


    @Test
    public void getProfilesNames() throws Exception {
        Map<Long, String> profilesNamesAndIds = new HashMap<>();
        profilesNamesAndIds.put(componentProfile1.getId(), componentProfile1.getDescription());
        profilesNamesAndIds.put(componentProfile2.getId(), componentProfile2.getDescription());
        profilesNamesAndIds.put(componentProfile3.getId(), componentProfile3.getDescription());
        profilesNamesAndIds.put(componentProfile4.getId(), componentProfile4.getDescription());

        Map<Long, String> result = componentProfileRepository.getProfilesNames();
        assertThat(result).isEqualTo(profilesNamesAndIds);
    }
}