package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.Config.ModelMapperConfig;
import ar.com.healthyapple.crm_web.Utils.ProductTypeDtoFactory;
import ar.com.healthyapple.crm_web.Utils.ProductTypeFactory;
import ar.com.healthyapple.crm_web.dto.Product.ProductTypeDto;
import ar.com.healthyapple.crm_web.model.Product.ProductType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
class ProductTypeDtoConverterTest {

    private static ModelMapper modelMapper;

    private static ProductTypeDtoConverter converter;

    @BeforeAll
    static void setUp() throws Exception {
        modelMapper = new ModelMapperConfig().modelMapper();
        converter = new ProductTypeDtoConverter(modelMapper);
    }

    @Test
    public void convertToDto_ProductTypeToProductTypeDto_ReturnsProductTypeDto() {
        ProductType productType = ProductTypeFactory.makeProductType();
        ProductTypeDto result = converter.convertToDto(productType);

        assertThat(result.getId()).isEqualTo(productType.getId());
        assertThat(result.getName()).isEqualTo(productType.getName());
    }

    @Test
     void convertToDto_NullProductType_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntity(null));

    }

    @Test
     void convertToEntity_ProductTypeDtoToProductType_ReturnsProductType() {
        ProductTypeDto productTypeDto = ProductTypeDtoFactory.makeProductTypeDto();
        ProductType result = converter.convertToEntity(productTypeDto);

        assertThat(result.getId()).isEqualTo(productTypeDto.getId());
        assertThat(result.getName()).isEqualTo(productTypeDto.getName());
    }


    @Test
     void convertToEntity_NullProductTypeDto_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntity(null));
    }
}