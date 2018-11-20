package ar.com.healthyapple.crm_web.service.Product;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Product.Component;
import ar.com.healthyapple.crm_web.model.Product.ComponentType;
import ar.com.healthyapple.crm_web.model.Product.Specification;
import ar.com.healthyapple.crm_web.repository.Product.ComponentRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ComponentServiceTest {

    private static final Long ID = 1L;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private ComponentService componentService;

    @MockBean
    private ComponentRepository componentRepository;

    @MockBean
    private ComponentType componentType;

    private Component componentRequest;

    private Component componentResponse;

    private Specification specification;

    private Specification specification1;

    @Before
    public void setUp() {
        specification = Mockito.mock(Specification.class);
        specification1 = Mockito.mock(Specification.class);
        List<Specification> specificationList = new ArrayList<>(Arrays.asList(specification, specification1));
        componentRequest = new Component("Component Request", componentType, specificationList);
        componentResponse = new Component("Component Response", componentType, specificationList);
        componentResponse.setId(ID);
    }

    @Test
    public void create() throws AlreadyExistException {
        when(componentRepository.findByDescriptionIgnoreCase(componentRequest.getDescription())).thenReturn(Optional.empty());
        when(componentRepository.save(componentRequest)).thenReturn(componentResponse);
        Component result = componentService.create(componentRequest);

        assertThat(result).isEqualTo(componentResponse);
    }

    @Test
    public void read() {
        componentRequest.setId(1L);
        when(componentRepository.findById(componentRequest.getId()))
                .thenReturn(Optional.of(componentResponse));
        Component result = componentService.read(ID);
        assertThat(result).isEqualTo(componentResponse);
    }

    @Test
    public void readByNonExistentIdShouldThrowNotFoundException() {
        thrown.expect(NotFoundException.class);
        when(componentRepository.findById(componentRequest.getId())).thenReturn(Optional.empty());
        Component result = componentService.read(ID);
    }

    @Test
    public void update() {
        componentRequest.setId(1L);
        when(componentRepository.findById(1L)).thenReturn(Optional.of(componentResponse));
        Component result = componentService.read(componentRequest.getId());
        assertThat(result).isEqualTo(componentResponse);
    }

    @Test
    public void deleteById() {
        componentRequest.setId(1L);
        when(componentRepository.findById(componentRequest.getId()))
                .thenReturn(Optional.of(componentResponse));
        doNothing().when(componentRepository).deleteById(ID);
        componentService.deleteById(ID);
    }

    @Test
    public void deleteByNonExistentIdShouldThrowNotFoundException() throws NotFoundException {
        thrown.expect(NotFoundException.class);
        componentRequest.setId(1L);
        when(componentRepository.findById(componentRequest.getId()))
                .thenReturn(Optional.empty());
        doNothing().when(componentRepository).deleteById(ID);
        componentService.deleteById(ID);
    }

    @Test
    public void delete() {
        componentRequest.setId(1L);
        when(componentRepository.findById(componentRequest.getId()))
                .thenReturn(Optional.of(componentResponse));
        doNothing().when(componentRepository).delete(componentRequest);
        componentService.deleteById(ID);
    }

    @Test
    public void deleteByNonExistentResourceShouldThrowNotFoundException() {
        thrown.expect(NotFoundException.class);
        when(componentRepository.findById(componentRequest.getId()))
                .thenReturn(Optional.empty());
        doNothing().when(componentRepository).delete(componentRequest);
        componentService.delete(componentRequest);
    }

    @TestConfiguration
    static class ComponentTestContextConfiguration {

        @Bean
        public ComponentService componentService(ComponentRepository componentRepository) {
            return new ComponentServiceImpl(componentRepository);
        }
    }
}