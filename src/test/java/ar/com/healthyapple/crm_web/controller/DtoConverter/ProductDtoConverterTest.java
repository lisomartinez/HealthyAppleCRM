package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.Config.ModelMapperConfig;
import ar.com.healthyapple.crm_web.Utils.ProductDtoFactory;
import ar.com.healthyapple.crm_web.Utils.ProductFactory;
import ar.com.healthyapple.crm_web.dto.Product.ComponentDto;
import ar.com.healthyapple.crm_web.dto.Product.ProductDto;
import ar.com.healthyapple.crm_web.dto.Product.ProductTypeDto;
import ar.com.healthyapple.crm_web.model.Product.Component;
import ar.com.healthyapple.crm_web.model.Product.Product;
import ar.com.healthyapple.crm_web.model.Product.ProductType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
 class ProductDtoConverterTest {

    private static ModelMapperConfig modelMapperConfig;

    private static ProductDtoConverter converter;

    private static ComponentDtoConverter componentDtoConverter;

    private static ProductTypeDtoConverter productTypeDtoConverter;

    @BeforeAll
    static void setUp() throws Exception {
        modelMapperConfig = new ModelMapperConfig();
        ModelMapper modelMapper = modelMapperConfig.modelMapper();
        converter = new ProductDtoConverter(modelMapper);
        componentDtoConverter = new ComponentDtoConverter(modelMapper);
        productTypeDtoConverter = new ProductTypeDtoConverter(modelMapper);
    }

    @Test
     void convertToEntity_Component_ReturnsComponentDto() {
        ProductDto productDto = ProductDtoFactory.makeProductDto();
        Product result = converter.convertToEntity(productDto);

        List<Component> componentDtoList = productDto.getComponents()
                .stream()
                .map(componentDto -> componentDtoConverter.convertToEntity(componentDto))
                .collect(Collectors.toList());

        ProductType productType = productTypeDtoConverter.convertToEntity(productDto.getProductType());

        assertThat(result.getId()).isEqualTo(productDto.getId());
        assertThat(result.getDescription()).isEqualTo(productDto.getDescription());
        assertThat(result.getComponents()).isEqualTo(componentDtoList);
        assertThat(result.getProductType()).isEqualTo(productType);
    }

    @Test
     void convertToEntity_Null_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntity(null));
    }

    @Test
     void convertToDto_ComponentDto_ReturnsComponent() {
        Product product = ProductFactory.makeProduct();
        ProductDto result = converter.convertToDto(product);

        List<ComponentDto> componentDtoList = product.getComponents()
                .stream()
                .map(componentDto -> componentDtoConverter.convertToDto(componentDto))
                .collect(Collectors.toList());

        ProductTypeDto productType = productTypeDtoConverter.convertToDto(product.getProductType());

        assertThat(result.getId()).isEqualTo(product.getId());
        assertThat(result.getDescription()).isEqualTo(product.getDescription());
        assertThat(result.getComponents()).isEqualTo(componentDtoList);
        assertThat(result.getProductType()).isEqualTo(productType);
    }

    @Test
     void convertToDto_Null_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToDto(null));
    }
}