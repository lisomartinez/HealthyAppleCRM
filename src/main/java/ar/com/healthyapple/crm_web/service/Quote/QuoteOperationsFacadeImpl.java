package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.exceptions.QuoteMalformedException;
import ar.com.healthyapple.crm_web.exceptions.QuoteVersionMismatchException;
import ar.com.healthyapple.crm_web.model.Product.Product;
import ar.com.healthyapple.crm_web.model.Product.ProductState;
import ar.com.healthyapple.crm_web.model.Product.StateBasedProduct;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteItem;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;
import ar.com.healthyapple.crm_web.service.Product.ProductService;
import ar.com.healthyapple.crm_web.service.Product.StateBasedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuoteOperationsFacadeImpl implements QuoteOperationsFacade {

    private Quote newQuote;

    private QuoteService quoteService;

    private QuoteListService quoteListService;

    private ProductService productService;

    private StateBasedProductService stateBasedProductService;

    private QuoteItemService quoteItemService;

    @Autowired
    public QuoteOperationsFacadeImpl(QuoteService quoteService, QuoteListService quoteListService, ProductService productService, StateBasedProductService stateBasedProductService, QuoteItemService quoteItemService) {
        this.quoteService = quoteService;
        this.quoteListService = quoteListService;
        this.productService = productService;
        this.stateBasedProductService = stateBasedProductService;
        this.quoteItemService = quoteItemService;
    }

    @Override
    public void request(Quote quote) {
        assert quote != null : "Quote could not be null";
            this.newQuote = quoteService.createQuoteFromRequest(quote);
            quoteListService.createQuoteList(newQuote);
    }


    @Override
    public void create(Quote quote) throws QuoteMalformedException {
        if (!isValidQuote(quote)) {
            throw new QuoteMalformedException("Invalid Quote");
        }
        this.newQuote = saveQuote(quote, QuoteState.QUOTED);
    }

    private boolean isValidQuote(Quote quote) {
        if (quote == null) return false;

        boolean itNotHasNullClient = quote.getClient() != null;
        boolean itHasAtLeastOneProductItem = quote.getProducts().size() != 0;
        boolean itsProductHasAtLeastOneProduct = quote.getProducts()
                .stream()
                .map(StateBasedProduct::getProduct)
                .noneMatch(Objects::isNull);
        boolean itsProductsHasAtLeastOneQuoteItem = quote.getProducts()
                .stream()
                .map(StateBasedProduct::getItems)
                .noneMatch(List::isEmpty);
        boolean itsProductsQuoteItemsComponentIsNotNull = quote.getProducts()
                .stream()
                .map(StateBasedProduct::getItems)
                .flatMap(Collection::stream)
                .noneMatch(Objects::isNull);

        return itNotHasNullClient
            && itHasAtLeastOneProductItem
            && itsProductHasAtLeastOneProduct
            && itsProductsHasAtLeastOneQuoteItem
            && itsProductsQuoteItemsComponentIsNotNull;

    }


    private List<StateBasedProduct> createStateBasedProducts(Quote quote) {
        return quote.getProducts()
                .stream()
                .map(stateBasedProductService::create)
                .collect(Collectors.toList());
    }


    private void setQuoteStateBasedProductToSavedProducts(Quote quote) {
        for (StateBasedProduct stateBasedProduct : quote.getProducts() ) {
            if(isNewProduct(stateBasedProduct)) {
                Product product = stateBasedProduct.getProduct();
                Product savedProduct = productService.create(product);
                List<QuoteItem> savedItems = stateBasedProduct.getItems()
                        .stream()
                        .map(quoteItem -> quoteItemService.create(quoteItem))
                        .collect(Collectors.toList());
                stateBasedProduct.setItems(savedItems);
                stateBasedProduct.setProduct(savedProduct);
            }
        }
    }

    private boolean isNewProduct(StateBasedProduct stateBasedProduct) {
        return stateBasedProduct.getState().equals(ProductState.NEW);
    }

    private Quote saveQuote(Quote quote, QuoteState quoteState) {
        assert quote != null : "Quote could not be null";
        assert quoteState != null: "Quote State could not be null";

        Quote savedQuote = quoteService.updateQuote(quote, quoteState);
        quoteListService.addQuoteToQuoteList(savedQuote);
        return savedQuote;
    }

    @Override
    public void cancel(Quote quote) throws QuoteMalformedException {

        if (!isValidQuote(quote)) {
            throw new QuoteMalformedException("Invalid Quote");
        }

        if (quoteListService.isQuoteTheLastVersionInList(quote)) {
            this.newQuote =  saveQuote(quote, QuoteState.CANCELED);
        } else {
            throw new QuoteVersionMismatchException("the quote is not the last version.");
        }    }

    @Override
    public void modify(Quote quote) throws QuoteMalformedException, QuoteVersionMismatchException{
        if (!isValidQuote(quote)) {
            throw new QuoteMalformedException("Invalid Quote");
        }
        if (quoteListService.isQuoteTheLastVersionInList(quote)) {
            this.newQuote =  saveQuote(quote, QuoteState.MODIFIED);
        } else {
            throw new QuoteVersionMismatchException("the quote is not the last version.");
        }
    }

    @Override
    public void reject(Quote quote) {
        if (!isValidQuote(quote)) {
            throw new QuoteMalformedException("Invalid Quote");
        }
        if (quoteListService.isQuoteTheLastVersionInList(quote)) {
            this.newQuote =  saveQuote(quote, QuoteState.REJECTED);
        } else {
            throw new QuoteVersionMismatchException("the quote is not the last version.");
        }


        this.newQuote = saveQuote(quote, QuoteState.REJECTED);
    }

    @Override
    public void accept(Quote quote) {
        if (!isValidQuote(quote)) {
            throw new QuoteMalformedException("Invalid Quote");
        }

        if (quoteListService.isQuoteTheLastVersionInList(quote)) {
            this.newQuote =  saveQuote(quote, QuoteState.ACCEPTED);
        } else {
            throw new QuoteVersionMismatchException("the quote is not the last version.");
        }
    }

    @Override
    public void send(Quote quote)  {
        if (!isValidQuote(quote)) {
            throw new QuoteMalformedException("Invalid Quote");
        }
        if (quoteListService.isQuoteTheLastVersionInList(quote)) {
            this.newQuote =  saveQuote(quote, QuoteState.WAITING_RESPONSE);
        } else {
            throw new QuoteVersionMismatchException("the quote is not the last version.");
        }
    }

    @Override
    public Quote getQuote() {
        return this.newQuote;
    }
}
