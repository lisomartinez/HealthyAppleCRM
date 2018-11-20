package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.dto.quote.QuoteItemDto;
import ar.com.healthyapple.crm_web.model.Quote.QuoteItem;

import java.math.BigDecimal;

public class QuoteItemDtoFactory {
    public static QuoteItemDto makeQuoteItemDto() {
        QuoteItemDto quoteItem = new QuoteItemDto();
        quoteItem.setId(1L);
        quoteItem.setDescription("Description");
        quoteItem.setCost(BigDecimal.valueOf(100));
        quoteItem.setPrice(BigDecimal.valueOf(100));
        quoteItem.setComponent(ComponentDtoFactory.makeComponentDto());
        return quoteItem;
    }
}
