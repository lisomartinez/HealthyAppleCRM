package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.Config.ModelMapperConfig;
import ar.com.healthyapple.crm_web.Utils.QuoteItemDtoFactory;
import ar.com.healthyapple.crm_web.Utils.QuoteItemFactory;
import ar.com.healthyapple.crm_web.dto.Product.ComponentDto;
import ar.com.healthyapple.crm_web.dto.quote.QuoteItemDto;
import ar.com.healthyapple.crm_web.model.Product.Component;
import ar.com.healthyapple.crm_web.model.Quote.QuoteItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class QuoteItemDtoConverterTest {


    private static ModelMapperConfig modelMapperConfig;

    private static QuoteItemDtoConverter converter;

    private static ComponentDtoConverter componentDtoConverter;

    @BeforeAll
     static void setUp() throws Exception {
        modelMapperConfig = new ModelMapperConfig();
        ModelMapper modelMapper = modelMapperConfig.modelMapper();
        converter = new QuoteItemDtoConverter(modelMapper);
        componentDtoConverter = new ComponentDtoConverter(modelMapper);
    }

    @Test
     void convertToDto_QuoteItem_ReturnsQuoteItemDto() {
        QuoteItem quoteItem = QuoteItemFactory.makeQuoteItem();
        QuoteItemDto result = converter.convertToDto(quoteItem);

        ComponentDto componentDto =  componentDtoConverter.convertToDto(quoteItem.getComponent());

        assertThat(result.getId()).isEqualTo(quoteItem.getId());
        assertThat(result.getDescription()).isEqualTo(quoteItem.getDescription());
        assertThat(result.getCost()).isEqualTo(quoteItem.getCost());
        assertThat(result.getPrice()).isEqualTo(quoteItem.getPrice());
        assertThat(result.getComponent()).isEqualTo(componentDto);

    }

    @Test
     void convertToDto_Null_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntity(null));
    }

    @Test
     void convertToEntity_QuoteItemDto_ReturnsQuoteItem() {
        QuoteItemDto quoteItem = QuoteItemDtoFactory.makeQuoteItemDto();
        QuoteItem result = converter.convertToEntity(quoteItem);

        Component component =  componentDtoConverter.convertToEntity(quoteItem.getComponent());

        assertThat(result.getId()).isEqualTo(quoteItem.getId());
        assertThat(result.getDescription()).isEqualTo(quoteItem.getDescription());
        assertThat(result.getCost()).isEqualTo(quoteItem.getCost());
        assertThat(result.getPrice()).isEqualTo(quoteItem.getPrice());
        assertThat(result.getComponent()).isEqualTo(component);
    }

    @Test
     void convertToEntity_Null_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntity(null));
    }
}