package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.dto.quote.QuoteDto;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class QuoteDtoFactory {
    public static QuoteDto makeQuoteDto() {
        QuoteDto quoteDto = new QuoteDto();
        quoteDto.setId(1L);
        quoteDto.setNumber(1L);
        quoteDto.setDescription("description");
        quoteDto.setVersion(1);
        quoteDto.setStatus(QuoteState.NEW);
        quoteDto.setCreated("2010-09-11 10:00");
        quoteDto.setCost(BigDecimal.valueOf(100));
        quoteDto.setPrice(BigDecimal.valueOf(100));
        quoteDto.setClientId(1L);
        quoteDto.setProducts(new ArrayList<>(Arrays.asList(StateBasedProductDtoFactory.makeProductDto())));

        return quoteDto;
    }
}
