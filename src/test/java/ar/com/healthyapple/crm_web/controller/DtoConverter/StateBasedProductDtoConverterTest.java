package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.Config.ModelMapperConfig;
import ar.com.healthyapple.crm_web.Utils.StateBasedProductDtoFactory;
import ar.com.healthyapple.crm_web.Utils.StateBasedProductFactory;
import ar.com.healthyapple.crm_web.dto.Product.ProductDto;
import ar.com.healthyapple.crm_web.dto.Product.StateBasedProductDto;
import ar.com.healthyapple.crm_web.model.Product.Product;
import ar.com.healthyapple.crm_web.model.Product.StateBasedProduct;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
class StateBasedProductDtoConverterTest {

    private static ModelMapperConfig modelMapperConfig;

    private static StateBasedProductDtoConverter converter;

    private static ProductDtoConverter productDtoConverter;

    @BeforeAll
    static void setUp() {
        modelMapperConfig = new ModelMapperConfig();
        ModelMapper modelMapper = modelMapperConfig.modelMapper();
        converter = new StateBasedProductDtoConverter(modelMapper);
        productDtoConverter = new ProductDtoConverter(modelMapper);
    }

    @Test
    void convertToDto() {
        StateBasedProduct stateBasedProduct = StateBasedProductFactory.makeProduct();
        StateBasedProductDto result = converter.convertToDto(stateBasedProduct);

        ProductDto productDto = productDtoConverter.convertToDto(stateBasedProduct.getProduct());

        assertThat(result.getId()).isEqualTo(stateBasedProduct.getId());
        assertThat(result.getState()).isEqualTo(stateBasedProduct.getState());
        assertThat(result.getProduct()).isEqualTo(productDto);
    }

    @Test
    void convertToDtoWithNull() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntity(null));
    }

    @Test
    void convertToEntity() {
        StateBasedProductDto stateBasedProduct = StateBasedProductDtoFactory.makeProductDto();
        StateBasedProduct result = converter.convertToEntity(stateBasedProduct);

        Product product = productDtoConverter.convertToEntity(stateBasedProduct.getProduct());

        assertThat(result.getId()).isEqualTo(stateBasedProduct.getId());
        assertThat(result.getState()).isEqualTo(stateBasedProduct.getState());
        assertThat(result.getProduct()).isEqualTo(product);
    }

    @Test
    void convertToEntityWithNull() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntity(null));
    }
}