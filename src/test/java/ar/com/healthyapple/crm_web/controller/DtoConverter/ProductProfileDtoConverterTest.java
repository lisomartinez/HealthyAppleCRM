package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.Config.ModelMapperConfig;
import ar.com.healthyapple.crm_web.Utils.ProductProfileDtoFactory;
import ar.com.healthyapple.crm_web.Utils.ProductProfileFactory;
import ar.com.healthyapple.crm_web.dto.Product.ComponentProfileDto;
import ar.com.healthyapple.crm_web.dto.Product.ProductProfileDto;
import ar.com.healthyapple.crm_web.model.Product.ComponentProfile;
import ar.com.healthyapple.crm_web.model.Product.ProductProfile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class ProductProfileDtoConverterTest {

    private static ModelMapperConfig modelMapperConfig;

    private static ProductProfileDtoConverter converter;

    private static ComponentProfileDtoConverter profileDtoConverter;


    @BeforeAll
    static void setUp() throws Exception {
        modelMapperConfig = new ModelMapperConfig();
        converter = new ProductProfileDtoConverter(modelMapperConfig.modelMapper());
        profileDtoConverter = new ComponentProfileDtoConverter(modelMapperConfig.modelMapper());
    }

    @Test
     void convertToEntity_ProductProfileDto_ReturnsProductProfile() {
        ProductProfileDto productProfileDto = ProductProfileDtoFactory.makeProductProfileDto();
        ProductProfile result = converter.convertToEntity(productProfileDto);

        List<ComponentProfile> componentProfileList = productProfileDto.getComponents()
                .stream()
                .map(profileDtoConverter::convertToEntity).collect(Collectors.toList());


        assertThat(result.getId()).isEqualTo(productProfileDto.getId());
        assertThat(result.getDescription()).isEqualTo(productProfileDto.getDescription());
        assertThat(result.getType()).isEqualTo(productProfileDto.getType());
        assertThat(result.getComponents()).isEqualTo(componentProfileList);

    }

    @Test
    void convertToDto_ProductProfile_ReturnsProductProfileDto() {
        ProductProfile productProfile = ProductProfileFactory.makeProductProfile();
        ProductProfileDto result = converter.convertToDto(productProfile);

        List<ComponentProfileDto> componentProfileList = productProfile.getComponents()
                .stream()
                .map(profileDtoConverter::convertToDto).collect(Collectors.toList());


        assertThat(result.getId()).isEqualTo(productProfile.getId());
        assertThat(result.getDescription()).isEqualTo(productProfile.getDescription());
        assertThat(result.getType()).isEqualTo(productProfile.getType());
        assertThat(result.getComponents()).isEqualTo(componentProfileList);
    }

    @Test
     void convertToEntity_NullProductProfile_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntity(null));
    }

    @Test
     void convertToDto_NullProductProfileDto_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToDto(null));

    }
}