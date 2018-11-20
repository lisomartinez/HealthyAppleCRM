package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.Config.ModelMapperConfig;
import ar.com.healthyapple.crm_web.Utils.QuoteDtoFactory;
import ar.com.healthyapple.crm_web.Utils.QuoteFactory;
import ar.com.healthyapple.crm_web.dto.Client.ThinClientDto;
import ar.com.healthyapple.crm_web.dto.Product.StateBasedProductDto;
import ar.com.healthyapple.crm_web.dto.quote.QuoteDto;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Product.StateBasedProduct;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class QuoteDtoConverterTest {


    private static ModelMapperConfig modelMapper;

    private static StateBasedProductDtoConverter stateBasedProductDtoConverter;

    private static QuoteItemDtoConverter quoteItemDtoConverter;

    private static ThinClientDtoConverter thinClientDtoConverter;

    private static QuoteDtoConverter converter;


    @BeforeAll
     static void setUp() throws Exception {
        modelMapper = new ModelMapperConfig();
        ModelMapper modelMapper1 = modelMapper.modelMapper();
        converter = new QuoteDtoConverter(modelMapper1);
        stateBasedProductDtoConverter = new StateBasedProductDtoConverter(modelMapper1);
        quoteItemDtoConverter = new QuoteItemDtoConverter(modelMapper1);
        thinClientDtoConverter = new ThinClientDtoConverter(modelMapper1);
    }

    @Test
     void convertToEntity() {
        QuoteDto quote = QuoteDtoFactory.makeQuoteDto();


        List<StateBasedProduct> products = quote.getProducts()
                .stream()
                .map(stateBasedProductDtoConverter::convertToEntity)
                .collect(Collectors.toList());


        Quote result = converter.convertToEntity(quote);

        String created = result.getCreated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        assertThat(result.getId()).isEqualTo(quote.getId());
        assertThat(result.getNumber()).isEqualTo(quote.getNumber());
        assertThat(result.getVersion()).isEqualTo(quote.getVersion());
        assertThat(result.getDescription()).isEqualTo(quote.getDescription());
        assertThat(result.getStatus()).isEqualTo(quote.getStatus());
        assertThat(created).isEqualTo(quote.getCreated());
        assertThat(result.getCost()).isEqualTo(quote.getCost());
        assertThat(result.getPrice()).isEqualTo(quote.getPrice());
        assertThat(result.getClient().getId()).isEqualTo(quote.getClientId());
        assertThat(result.getProducts()).isEqualTo(products);
//        assertThat(result.getItems()).isEqualTo(quoteItems);
    }

    @Test
     void convertToDto() {
        Quote quote = QuoteFactory.makeQuote();

        ThinClientDto clientDto =  thinClientDtoConverter.convertToDto(quote.getClient());

        List<StateBasedProductDto> productDtos = quote.getProducts()
                .stream()
                .map(stateBasedProductDtoConverter::convertToDto)
                .collect(Collectors.toList());


        QuoteDto result = converter.convertToDto(quote);

        assertThat(result.getId()).isEqualTo(quote.getId());
        assertThat(result.getNumber()).isEqualTo(quote.getNumber());
        assertThat(result.getVersion()).isEqualTo(quote.getVersion());
        assertThat(result.getDescription()).isEqualTo(quote.getDescription());
        assertThat(result.getStatus()).isEqualTo(quote.getStatus());
        assertThat(result.getCreated()).isEqualTo(quote.getCreated().toString());
        assertThat(result.getCost()).isEqualTo(quote.getCost());
        assertThat(result.getPrice()).isEqualTo(quote.getPrice());
        assertThat(result.getClientId()).isEqualTo(clientDto);
        assertThat(result.getProducts()).isEqualTo(productDtos);
    }

    @Test
     void convertToEntityWithNull() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntity(null));
    }

    @Test
     void convertToDtoWithNull() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntity(null));
    }
}