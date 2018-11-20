package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.Config.ModelMapperConfig;
import ar.com.healthyapple.crm_web.Utils.ComponentTypeDtoFactory;
import ar.com.healthyapple.crm_web.Utils.ComponentTypeFactory;
import ar.com.healthyapple.crm_web.dto.Product.ComponentTypeDto;
import ar.com.healthyapple.crm_web.model.Product.ComponentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
class ComponentTypeDtoConverterTest {

    private static ModelMapperConfig modelMapperConfig;

    private static ComponentTypeDtoConverter converter;

    @BeforeAll
    static void setUp() throws Exception {
        modelMapperConfig = new ModelMapperConfig();
        ModelMapper modelMapper = modelMapperConfig.modelMapper();
        converter = new ComponentTypeDtoConverter(modelMapper);
    }

    @Test
     void convertToEntity_ComponentTypeDto_ReturnsComponentType() {
        ComponentTypeDto componentTypeDto = ComponentTypeDtoFactory.makeComponentTypeDto();
        ComponentType result = converter.convertToEntity(componentTypeDto);

        assertThat(result.getId()).isEqualTo(componentTypeDto.getId());
        assertThat(result.getName()).isEqualTo(componentTypeDto.getName());
    }

    @Test
     void convertToEntity_Null_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntity(null));
    }


    @Test
     void convertToDto_ComponentType_ReturnsComponentTypeDto() {
        ComponentType componentType = ComponentTypeFactory.makeComponentType();
        ComponentTypeDto result = converter.convertToDto(componentType);

        assertThat(result.getId()).isEqualTo(componentType.getId());
        assertThat(result.getName()).isEqualTo(componentType.getName());
    }

    @Test
     void convertToDto_Null_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToDto(null));

    }
}