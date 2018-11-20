package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.Config.ModelMapperConfig;
import ar.com.healthyapple.crm_web.Utils.SaleDtoFactory;
import ar.com.healthyapple.crm_web.Utils.SaleFactory;
import ar.com.healthyapple.crm_web.dto.Client.ThinClientDto;
import ar.com.healthyapple.crm_web.dto.Product.StateBasedProductDto;
import ar.com.healthyapple.crm_web.dto.Sale.SaleDto;
import ar.com.healthyapple.crm_web.dto.Sale.SaleItemDto;
import ar.com.healthyapple.crm_web.dto.quote.QuoteDto;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Product.StateBasedProduct;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Sale.Sale;

import ar.com.healthyapple.crm_web.model.Sale.SaleItem;
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
public class SaleDtoConverterTest {



    private static ModelMapperConfig modelMapperConfig;

    private static StateBasedProductDtoConverter stateBasedProductDtoConverter;

    private static ThinClientDtoConverter clientDtoConverter;

    private static SaleItemDtoConverter saleItemDtoConverter;

    private static QuoteDtoConverter quoteDtoConverter;

    private static SaleDtoConverter converter;


    @BeforeAll
    static void setUp() throws Exception {
        modelMapperConfig = new ModelMapperConfig();
        ModelMapper modelMapper = modelMapperConfig.modelMapper();
        converter = new SaleDtoConverter(modelMapper);
        stateBasedProductDtoConverter = new StateBasedProductDtoConverter(modelMapper);
        clientDtoConverter = new ThinClientDtoConverter(modelMapper);
        saleItemDtoConverter = new SaleItemDtoConverter(modelMapper);
        quoteDtoConverter = new QuoteDtoConverter(modelMapper);
    }

    @Test
    public void convertToEntity_SaleDto_ReturnsSale() {
        SaleDto saleDto = SaleDtoFactory.makeSaleDto();

        Quote quote = quoteDtoConverter.convertToEntity(saleDto.getQuote());
        Sale result = converter.convertToEntity(saleDto);

        String startDate = result.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        assertThat(result.getId()).isEqualTo(saleDto.getId());
        assertThat(result.getDescription()).isEqualTo(saleDto.getDescription());
        assertThat(result.getState()).isEqualTo(saleDto.getState());
        assertThat(result.getStartDate()).isEqualTo(startDate);
        assertThat(result.getQuote()).isEqualTo(quote);
    }

    @Test
    public void convertToDto_Sale_ReturnsSaleDto() {
        Sale sale = SaleFactory.makeSale();

        QuoteDto quote = quoteDtoConverter.convertToDto(sale.getQuote());

        SaleDto result = converter.convertToDto(sale);


        assertThat(result.getId()).isEqualTo(sale.getId());
        assertThat(result.getDescription()).isEqualTo(sale.getDescription());
        assertThat(result.getState()).isEqualTo(sale.getState());
        assertThat(result.getStartDate()).isEqualTo(sale.getStartDate().toString());

        assertThat(result.getQuote()).isEqualTo(quote);
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