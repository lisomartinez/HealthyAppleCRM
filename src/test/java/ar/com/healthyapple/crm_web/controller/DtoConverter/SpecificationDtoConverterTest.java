package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.Config.ModelMapperConfig;
import ar.com.healthyapple.crm_web.Utils.SpecificationDtoFactory;
import ar.com.healthyapple.crm_web.Utils.SpecificationFactory;
import ar.com.healthyapple.crm_web.dto.Product.SpecificationDto;
import ar.com.healthyapple.crm_web.model.Product.Specification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class SpecificationDtoConverterTest {

    private static ModelMapperConfig modelMapperConfig;

    private static SpecificationDtoConverter converter;

    @BeforeAll
    static void setUp() {
        modelMapperConfig = new ModelMapperConfig();
        ModelMapper modelMapper = modelMapperConfig.modelMapper();
        converter = new SpecificationDtoConverter(modelMapper);
    }

    @Test
    void convertToDto() {
        Specification specification = SpecificationFactory.makeSpecification();
        SpecificationDto result = converter.convertToDto(specification);

        assertThat(result.getId()).isEqualTo(specification.getId());
        assertThat(result.getName()).isEqualTo(specification.getName());
        assertThat(result.getDescription()).isEqualTo(specification.getDescription());
    }

    @Test
    void convertToDtoWithNull() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntity(null));
    }

    @Test
    void convertToEntity() {
        SpecificationDto specification = SpecificationDtoFactory.makeSpecificationDto();
        Specification result = converter.convertToEntity(specification);

        assertThat(result.getId()).isEqualTo(specification.getId());
        assertThat(result.getName()).isEqualTo(specification.getName());
        assertThat(result.getDescription()).isEqualTo(specification.getDescription());
    }

    @Test
    void convertToEntityWithNull() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntity(null));
    }
}