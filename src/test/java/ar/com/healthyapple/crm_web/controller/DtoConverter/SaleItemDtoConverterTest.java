package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.Config.ModelMapperConfig;
import ar.com.healthyapple.crm_web.Utils.SaleItemDtoFactory;
import ar.com.healthyapple.crm_web.Utils.SaleItemFactory;
import ar.com.healthyapple.crm_web.dto.Sale.SaleItemDto;
import ar.com.healthyapple.crm_web.model.Sale.SaleItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
class SaleItemDtoConverterTest {

    private static ModelMapperConfig modelMapperConfig;

    private static SaleItemDtoConverter converter;

    @BeforeAll
    void setUp() {
        ModelMapper modelMapper = modelMapperConfig.modelMapper();
        converter = new SaleItemDtoConverter(modelMapper);
    }

    @Test
    @Disabled
    void convertToDto() {
    }

    @Test
    void convertToDtoWithNull() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntity(null));
    }


    @Test
    @Disabled
    void convertToEntity() {
    }

    @Test
    void convertToEntityWithNull() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntity(null));
    }
}