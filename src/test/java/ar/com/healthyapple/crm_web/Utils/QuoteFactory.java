package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.model.Product.StateBasedProduct;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteItem;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class QuoteFactory {
    public static Quote makeQuote() {
        Quote quote = new Quote();
        quote.setId(1L);
        quote.setNumber(1L);
        quote.setDescription("description");
        quote.setVersion(1);
        quote.setStatus(QuoteState.NEW);
        quote.setCreated(LocalDateTime.of(2010, 9, 11, 22,00));
        quote.setCost(BigDecimal.valueOf(100));
        quote.setPrice(BigDecimal.valueOf(100));
        quote.setClient(ClientFactory.makeClient());
        quote.setProducts(new ArrayList<>(Arrays.asList(StateBasedProductFactory.makeProduct())));
//        quote.setItems(new ArrayList<>(Arrays.asList(QuoteItemFactory.makeQuoteItem())));
        return quote;
    }


    public static Quote makeAskQuote() {
        Quote quote = new Quote();
        quote.setDescription("description");
        quote.setStatus(QuoteState.ASK);
        quote.setClient(ClientFactory.makeClient());
        return quote;
    }

}
