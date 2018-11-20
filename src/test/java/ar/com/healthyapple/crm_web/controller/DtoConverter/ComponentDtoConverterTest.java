package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.Config.ModelMapperConfig;
import ar.com.healthyapple.crm_web.Utils.ComponentDtoFactory;
import ar.com.healthyapple.crm_web.Utils.ComponentFactory;
import ar.com.healthyapple.crm_web.dto.Product.ComponentDto;
import ar.com.healthyapple.crm_web.dto.Product.ComponentTypeDto;
import ar.com.healthyapple.crm_web.dto.Product.SpecificationDto;
import ar.com.healthyapple.crm_web.model.Product.Component;
import ar.com.healthyapple.crm_web.model.Product.ComponentType;
import ar.com.healthyapple.crm_web.model.Product.Specification;

import org.junit.Rule;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;

import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
public class ComponentDtoConverterTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private static ModelMapperConfig modelMapperConfig;

    private static ComponentDtoConverter converter;

    private static SpecificationDtoConverter specificationDtoConverter;

    private static ComponentTypeDtoConverter componentTypeDtoConverter;

    @BeforeAll
    static void setUp() throws Exception {
        modelMapperConfig = new ModelMapperConfig();
        ModelMapper modelMapper = modelMapperConfig.modelMapper();
        converter = new ComponentDtoConverter(modelMapper);
        specificationDtoConverter = new SpecificationDtoConverter(modelMapper);
        componentTypeDtoConverter = new ComponentTypeDtoConverter(modelMapper);
    }

    @Test
    void convertToEntity_ComponentDtoToComponent_ReturnComponent() {
        ComponentDto componentDto = ComponentDtoFactory.makeComponentDto();
        Component result = converter.convertToEntity(componentDto);

        List<Specification> specificationDtoList = componentDto.getSpecifications()
                .stream()
                .map(specification -> specificationDtoConverter.convertToEntity(specification))
                .collect(Collectors.toList());

        ComponentType componentType = componentTypeDtoConverter.convertToEntity(componentDto.getComponentType());

        assertThat(result.getId()).isEqualTo(componentDto.getId());
        assertThat(result.getDescription()).isEqualTo(componentDto.getDescription());
        assertThat(result.getSpecifications()).isEqualTo(specificationDtoList);
        assertThat(result.getComponentType()).isEqualTo(componentType);

    }

    @Test
    void convertToEntity_NullComponentDto_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntity(null) );
    }

    @Test
     void convertToDto_ComponentToComponentDto_ReturnsComponentDto() {
        Component component = ComponentFactory.makeComponent();
        ComponentDto result = converter.convertToDto(component);

        List<SpecificationDto> specificationDtoList = component.getSpecifications()
                .stream()
                .map(specification -> specificationDtoConverter.convertToDto(specification))
                .collect(Collectors.toList());

        ComponentTypeDto componentType = componentTypeDtoConverter.convertToDto(component.getComponentType());

        assertThat(result.getId()).isEqualTo(component.getId());
        assertThat(result.getDescription()).isEqualTo(component.getDescription());
        assertThat(result.getSpecifications()).isEqualTo(specificationDtoList);
        assertThat(result.getComponentType()).isEqualTo(componentType);
    }

    @Test
     void convertToDto_NullComponent_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToDto(null) );
    }
}