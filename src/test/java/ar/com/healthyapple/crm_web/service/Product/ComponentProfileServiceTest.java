package ar.com.healthyapple.crm_web.service.Product;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Product.Component;
import ar.com.healthyapple.crm_web.model.Product.ComponentProfile;
import ar.com.healthyapple.crm_web.repository.Product.ComponentProfileRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ComponentProfileServiceTest {

    private static final Long ID = 1L;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @TestConfiguration
    static class ComponentProfileServiceTestContextConfiguration {

        @Bean
        public ComponentProfileService componentProfileService(ComponentProfileRepository componentProfileRepository) {
            return new ComponentProfileServiceImpl(componentProfileRepository);
        }
    }

    @Autowired
    private ComponentProfileService componentProfileService;

    @MockBean
    private ComponentProfileRepository componentProfileRepository;

    private ComponentProfile componentProfile1;

    private ComponentProfile componentProfile2;

    private ComponentProfile componentProfile3;

    private ComponentProfile componentProfile4;

    private ComponentProfile componentProfile5;


    @Before
    public void setUp() throws Exception {
        Map<String, String> specifications = new HashMap<>();

        componentProfile1 = new ComponentProfile("type_of_component1", "description_1", Boolean.FALSE, specifications);
        componentProfile1.setId(1L);

        componentProfile2 = new ComponentProfile("type_of_component2", "description_2", Boolean.FALSE, specifications);
        componentProfile2.setId(2L);

        componentProfile3 = new ComponentProfile("type_of_component3", "description_3", Boolean.FALSE, specifications);
        componentProfile3.setId(3L);

        componentProfile4 = new ComponentProfile("type_of_component4", "description_4", Boolean.FALSE, specifications);
        componentProfile4.setId(4L);

        componentProfile5 = new ComponentProfile("type_of_component5", "description_5", Boolean.FALSE, specifications);
        componentProfile5.setId(5L);
    }

    @Test
    public void create() {

        when(componentProfileRepository.findByTypeIgnoreCase(componentProfile1.getType())).thenReturn(Optional.empty());
        when(componentProfileRepository.save(componentProfile1)).thenReturn(componentProfile1);
        ComponentProfile result = componentProfileService.create(componentProfile1);
        assertThat(result).isEqualTo(componentProfile1);
    }

    @Test
    public void createDuplicateShouldThrowAlreadyExistsException() {
        exception.expect(AlreadyExistException.class);
        when(componentProfileRepository.findByTypeIgnoreCase(componentProfile1.getType())).thenReturn(Optional.of(componentProfile1));
        componentProfileService.create(componentProfile1);
    }

    @Test
    public void read() {
        when(componentProfileRepository.findById(ID)).thenReturn(Optional.of(componentProfile1));
        ComponentProfile result = componentProfileService.read(ID);
        assertThat(result).isEqualTo(componentProfile1);
    }

    @Test
    public void readNonExistentResourceShouldThrownNotFoundException() {
        exception.expect(NotFoundException.class);

        when(componentProfileRepository.findById(ID)).thenReturn(Optional.empty());
        componentProfileService.read(ID);
    }

    @Test
    public void update() {
        Map<String, String> specifications = new HashMap<>();
        ComponentProfile updatedComponentProfile = new ComponentProfile("Updated_type_of_component1", "description_1", Boolean.FALSE, specifications);
        updatedComponentProfile.setId(1L);

        when(componentProfileRepository.findById(ID)).thenReturn(Optional.of(componentProfile1));
        when(componentProfileRepository.save(updatedComponentProfile)).thenReturn(updatedComponentProfile);
        ComponentProfile result = componentProfileService.update(updatedComponentProfile);
        assertThat(result).isEqualTo(updatedComponentProfile);

    }

    @Test
    public void updateNonExsitentResourceShouldThrowNotFoundException() {
        exception.expect(NotFoundException.class);

        Map<String, String> specifications = new HashMap<>();
        ComponentProfile updatedComponentProfile = new ComponentProfile("Updated_type_of_component1", "description_1", Boolean.FALSE, specifications);
        updatedComponentProfile.setId(1L);

        when(componentProfileRepository.findById(ID)).thenReturn(Optional.empty());
        when(componentProfileRepository.save(updatedComponentProfile)).thenReturn(updatedComponentProfile);
        componentProfileService.update(updatedComponentProfile);

    }

    @Test
    public void findAll() {
        List<ComponentProfile> profiles = Arrays.asList(componentProfile1, componentProfile2, componentProfile3, componentProfile4, componentProfile5);
        when(componentProfileRepository.findAll()).thenReturn(profiles);
        List<ComponentProfile> result = componentProfileService.findAll();
        assertThat(result).isEqualTo(profiles);
    }

    @Test
    public void getProfileIdAndNames() {
        Map<Long, String> profilesIdsAndNames = new HashMap<>();
        profilesIdsAndNames.put(componentProfile1.getId(), componentProfile1.getDescription());
        profilesIdsAndNames.put(componentProfile2.getId(), componentProfile2.getDescription());
        profilesIdsAndNames.put(componentProfile3.getId(), componentProfile3.getDescription());
        profilesIdsAndNames.put(componentProfile4.getId(), componentProfile4.getDescription());
        profilesIdsAndNames.put(componentProfile5.getId(), componentProfile5.getDescription());

        when(componentProfileRepository.getProfilesNames()).thenReturn(profilesIdsAndNames);

        Map<Long,String> result = componentProfileService.getProfileIdAndNames();
        assertThat(result).isEqualTo(profilesIdsAndNames);
    }
}