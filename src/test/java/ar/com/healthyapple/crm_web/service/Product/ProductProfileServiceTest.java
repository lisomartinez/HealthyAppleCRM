package ar.com.healthyapple.crm_web.service.Product;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Product.ComponentProfile;
import ar.com.healthyapple.crm_web.model.Product.ProductProfile;
import ar.com.healthyapple.crm_web.repository.Product.ProductProfileRepository;
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
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProductProfileServiceTest {
    private static final Long ID = 1L;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @TestConfiguration
    static class ProductProfileServiceTestContextConfiguration {

        @Bean
        public ProductProfileService productProfileService(ProductProfileRepository productProfileRepository) {
            return new ProductProfileServiceImpl(productProfileRepository);
        }
    }

    @Autowired
    private ProductProfileService productProfileService;

    @MockBean
    private ProductProfileRepository productProfileRepository;

    @MockBean
    private ComponentProfile componentProfile;

    private ProductProfile productProfile1;

    private ProductProfile productProfile2;

    private ProductProfile productProfile3;

    private ProductProfile productProfile4;

    private ProductProfile productProfile5;


    @Before
    public void setUp() throws Exception {
        Map<String, String> specifications = new HashMap<>();

        List<ComponentProfile> components = new ArrayList<>(Arrays.asList(componentProfile));

        productProfile1 = new ProductProfile("type_of_product1", "description_1",components);
        productProfile1.setId(1L);

        productProfile2 = new ProductProfile("type_of_product2", "description_2",components);
        productProfile2.setId(2L);

        productProfile3 = new ProductProfile("type_of_product3", "description_3",components);
        productProfile3.setId(3L);

        productProfile4 = new ProductProfile("type_of_product4", "description_4",components);
        productProfile4.setId(4L);

        productProfile5 = new ProductProfile("type_of_product5", "description_5",components);
        productProfile5.setId(5L);
    }

    @Test
    public void create() {

        when(productProfileRepository.findByTypeIgnoreCase(productProfile1.getType())).thenReturn(Optional.empty());
        when(productProfileRepository.save(productProfile1)).thenReturn(productProfile1);
        ProductProfile result = productProfileService.create(productProfile1);
        assertThat(result).isEqualTo(productProfile1);
    }

    @Test
    public void createDuplicateShouldThrowAlreadyExistsException() {
        exception.expect(AlreadyExistException.class);
        when(productProfileRepository.findByTypeIgnoreCase(productProfile1.getType())).thenReturn(Optional.of(productProfile1));
        productProfileService.create(productProfile1);
    }

    @Test
    public void read() {
        when(productProfileRepository.findById(ID)).thenReturn(Optional.of(productProfile1));
        ProductProfile result = productProfileService.read(ID);
        assertThat(result).isEqualTo(productProfile1);
    }

    @Test
    public void readNonExistentResourceShouldThrownNotFoundException() {
        exception.expect(NotFoundException.class);

        when(productProfileRepository.findById(ID)).thenReturn(Optional.empty());
        productProfileService.read(ID);
    }

    @Test
    public void update() {
        List<ComponentProfile> components = new ArrayList<>(Arrays.asList(componentProfile));
        ProductProfile updatedProductProfile =  new ProductProfile("type_of_product1", "description_1",components);
        updatedProductProfile.setId(1L);

        when(productProfileRepository.findById(ID)).thenReturn(Optional.of(productProfile1));
        when(productProfileRepository.save(updatedProductProfile)).thenReturn(updatedProductProfile);
        ProductProfile result = productProfileService.update(updatedProductProfile);
        assertThat(result).isEqualTo(updatedProductProfile);

    }

    @Test
    public void updateNonExsitentResourceShouldThrowNotFoundException() {
        exception.expect(NotFoundException.class);

        List<ComponentProfile> components = new ArrayList<>(Arrays.asList(componentProfile));
        ProductProfile updatedProductProfile =  new ProductProfile("type_of_product1", "description_1",components);
        updatedProductProfile.setId(1L);

        when(productProfileRepository.findById(ID)).thenReturn(Optional.empty());
        when(productProfileRepository.save(updatedProductProfile)).thenReturn(updatedProductProfile);
        productProfileService.update(updatedProductProfile);

    }

    @Test
    public void findAll() {
        List<ProductProfile> profiles = Arrays.asList(productProfile1, productProfile2, productProfile3, productProfile4, productProfile5);
        when(productProfileRepository.findAll()).thenReturn(profiles);
        List<ProductProfile> result = productProfileService.findAll();
        assertThat(result).isEqualTo(profiles);
    }

    @Test
    public void  getProfileIdAndTypes() {
        Map<Long, String> profilesIdsAndNames = new HashMap<>();
        profilesIdsAndNames.put(productProfile1.getId(), productProfile1.getDescription());
        profilesIdsAndNames.put(productProfile2.getId(), productProfile2.getDescription());
        profilesIdsAndNames.put(productProfile3.getId(), productProfile3.getDescription());
        profilesIdsAndNames.put(productProfile4.getId(), productProfile4.getDescription());
        profilesIdsAndNames.put(productProfile5.getId(), productProfile5.getDescription());

        when(productProfileRepository.getProfilesNames()).thenReturn(profilesIdsAndNames);

        Map<Long,String> result = productProfileService.getProfileIdAndTypes();
        assertThat(result).isEqualTo(profilesIdsAndNames);
    }
    
    
    

}