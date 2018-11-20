package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.model.Quote.QuoteItem;

import java.math.BigDecimal;

public class QuoteItemFactory {
    public static QuoteItem makeQuoteItem() {
        QuoteItem quoteItem = new QuoteItem();
        quoteItem.setId(1L);
        quoteItem.setDescription("Description");
        quoteItem.setCost(BigDecimal.valueOf(100));
        quoteItem.setPrice(BigDecimal.valueOf(100));
        quoteItem.setComponent(ComponentFactory.makeComponent());
        return quoteItem;
    }
}
