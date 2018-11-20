package ar.com.healthyapple.crm_web.service.Quote;

import ar.com.healthyapple.crm_web.Utils.QuoteFactory;
import ar.com.healthyapple.crm_web.exceptions.QuoteMalformedException;
import ar.com.healthyapple.crm_web.exceptions.QuoteVersionMismatchException;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Product.Product;
import ar.com.healthyapple.crm_web.model.Product.ProductState;
import ar.com.healthyapple.crm_web.model.Product.StateBasedProduct;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteItem;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;
import ar.com.healthyapple.crm_web.repository.Client.ClientRepository;
import ar.com.healthyapple.crm_web.repository.Product.ProductRepository;
import ar.com.healthyapple.crm_web.repository.Product.StateBasedProductRepository;
import ar.com.healthyapple.crm_web.repository.Quote.QuoteItemRepository;
import ar.com.healthyapple.crm_web.repository.Quote.QuoteListRepository;
import ar.com.healthyapple.crm_web.repository.Quote.QuoteRepository;
import ar.com.healthyapple.crm_web.service.Product.ProductService;
import ar.com.healthyapple.crm_web.service.Product.ProductServiceImpl;
import ar.com.healthyapple.crm_web.service.Product.StateBasedProductService;
import ar.com.healthyapple.crm_web.service.Product.StateBasedProductServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class QuoteOperationsFacadeIT {

    private QuoteService quoteService;

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private QuoteListRepository quoteListRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private StateBasedProductRepository stateBasedProductRepository;

    @Autowired
    private QuoteItemRepository quoteItemRepository;

    private QuoteItemService quoteItemService;

    private QuoteListService quoteListService;

    private ProductService productService;

    private QuoteOperationsFacade operations;

    private StateBasedProductService stateBasedProductService;

    @BeforeEach
    void setUp() {
        quoteListService = new QuoteListServiceImpl(quoteListRepository);
        quoteService = new QuoteServiceImpl(quoteRepository);
        stateBasedProductService = new StateBasedProductServiceImpl(stateBasedProductRepository);
        productService = new ProductServiceImpl(productRepository);
        quoteItemService = new QuoteItemServiceImpl(quoteItemRepository);
        operations = new QuoteOperationsFacadeImpl(quoteService, quoteListService, productService, stateBasedProductService, quoteItemService);
    }

    @AfterEach
    void tearDown() {
        quoteListRepository.deleteAll();
        quoteRepository.deleteAll();
        stateBasedProductRepository.deleteAll();
        quoteItemRepository.deleteAll();
        productRepository.deleteAll();
        clientRepository.deleteAll();

    }

    @Test
    public void request_WellFormedQuote_ReturnsCreatedQuote() {
        Quote quote = QuoteFactory.makeAskQuote();

        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        Quote savedQuote = QuoteFactory.makeAskQuote();
        savedQuote.setClient(client);
        savedQuote.setId(1L);
        savedQuote.setNumber(0L);
        savedQuote.setVersion(0);
        savedQuote.setPrice(BigDecimal.valueOf(0));
        savedQuote.setCost(BigDecimal.valueOf(0));
        savedQuote.setStatus(QuoteState.REQUESTED);

        operations.request(quote);
        Quote result = operations.getQuote();

        assertThat(result.getNumber()).isEqualTo(savedQuote.getNumber());
        assertThat(result.getVersion()).isEqualTo(savedQuote.getVersion());
        assertThat(result.getDescription()).isEqualTo(savedQuote.getDescription());
        assertThat(result.getStatus()).isEqualTo(savedQuote.getStatus());
        assertThat(result.getCost()).isEqualTo(savedQuote.getCost());
        assertThat(result.getPrice()).isEqualTo(savedQuote.getPrice());
        assertThat(result.getClient()).isEqualTo(savedQuote.getClient());
        assertThat(result.getProducts()).isEqualTo(savedQuote.getProducts());
    }

    @Test
    public void request_WithNull_ThrowsException() {
    assertThrows(AssertionError.class, () -> operations.request(null));
    }

    @Test
    public void request_CreateQuoteList_ReturnsCreatedQuote() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        Quote savedQuote = QuoteFactory.makeAskQuote();
        savedQuote.setClient(client);
        savedQuote.setId(1L);
        savedQuote.setVersion(0);
        savedQuote.setPrice(BigDecimal.valueOf(0));
        savedQuote.setCost(BigDecimal.valueOf(0));
        savedQuote.setStatus(QuoteState.REQUESTED);

        operations.request(quote);
        Quote result = operations.getQuote();

        assertThat(quoteListRepository.findByQuoteNumber(result.getNumber())).isNotNull();
        assertThat(quoteListRepository.findByQuoteNumber(result.getNumber()).get().getQuoteNumber()).isEqualTo(result.getNumber());

    }

    @Test
    public void request_TwoRequests_ReturnsCreatedQuoteWithIncreaseQuoteNumber() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));

        Quote savedQuote = QuoteFactory.makeAskQuote();
        savedQuote.setClient(client);
        savedQuote.setId(1L);
        savedQuote.setNumber(0L);
        savedQuote.setVersion(0);
        savedQuote.setPrice(BigDecimal.valueOf(0));
        savedQuote.setCost(BigDecimal.valueOf(0));
        savedQuote.setStatus(QuoteState.REQUESTED);

        operations.request(quote);
        Quote result = operations.getQuote();
        assertThat(result).isEqualTo(savedQuote);



        savedQuote.setClient(client);
        savedQuote.setId(1L);
        savedQuote.setNumber(1L);
        savedQuote.setVersion(0);
        savedQuote.setPrice(BigDecimal.valueOf(0));
        savedQuote.setCost(BigDecimal.valueOf(0));
        savedQuote.setStatus(QuoteState.REQUESTED);

        operations.request(quote);
        result = operations.getQuote();
        assertThat(result).isEqualTo(savedQuote);
    }

    @Test
    void create_Quote_SetQuoteInstanceVariableToUpdatedQuote() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        operations.create(result);
        Quote created = operations.getQuote();

        assertThat(created).isNotNull();
        assertThat(created.getClient().getId()).isNotNull();

        created.getProducts()
                .stream()
                .map(StateBasedProduct::getId)
                .map(id ->
                        assertThat(id).isNotNull());

        created.getProducts()
               .stream()
                .map(StateBasedProduct::getProduct)
                .map(Product::getId)
                .map(id ->
                        assertThat(id).isNotNull());

        created.getProducts()
                .stream()
                .map(StateBasedProduct::getItems)
                .map(quoteItems -> quoteItems.stream().map(
                        QuoteItem::getId
                ))
                .map(id ->
                        assertThat(id).isNotNull());

//        quote.setProducts();
    }

    @Test
    void create_NullQuote_ThrowsQuoteMalformedException() {
        assertThrows(QuoteMalformedException.class, () -> operations.create(null));
    }

    @Test
    void create_QuoteWithNullClient_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(null);

        assertThrows(QuoteMalformedException.class, () -> operations.create(result));
    }


    @Test
    void create_QuoteWitEmptyProducts_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();


        result.setProducts(Arrays.asList());

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.create(result));
    }

    @Test
    void create_QuoteWithStateBasedProductWithOutProduct_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.create(result));
    }

    @Test
    void create_QuoteWithStateBasedProductWithEmptyQuoteList_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setItems(Arrays.asList());
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.create(result));
    }

    @Test
    void create_QuoteWithStateBasedProductWithQuoteItemsWithOutComponent_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.create(result));
    }

    @Test
    void cancel_ValidQuote_ReturnsQuote() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        operations.create(result);
        Quote created = operations.getQuote();

        operations.cancel(created);
        Quote canceled = operations.getQuote();

        assertThat(canceled).isNotNull();
        assertThat(canceled.getClient().getId()).isNotNull();

        canceled.getProducts()
                .stream()
                .map(StateBasedProduct::getId)
                .map(id ->
                        assertThat(id).isNotNull());

        canceled.getProducts()
                .stream()
                .map(StateBasedProduct::getProduct)
                .map(Product::getId)
                .map(id ->
                        assertThat(id).isNotNull());

        canceled.getProducts()
                .stream()
                .map(StateBasedProduct::getItems)
                .map(quoteItems -> quoteItems.stream().map(
                        QuoteItem::getId
                ))
                .map(id ->
                        assertThat(id).isNotNull());
        }


    @Test
    void cancel_NullQuote_ThrowsQuoteMalformedException() {
        assertThrows(QuoteMalformedException.class, () -> operations.create(null));
    }

    @Test
    void cancel_QuoteWithNullClient_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(null);

        assertThrows(QuoteMalformedException.class, () -> operations.cancel(result));
    }


    @Test
    void cancel_QuoteWitEmptyProducts_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();


        result.setProducts(Arrays.asList());

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.cancel(result));
    }

    @Test
    void cancel_QuoteWithStateBasedProductWithOutProduct_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.cancel(result));
    }

    @Test
    void cancel_QuoteWithStateBasedProductWithEmptyQuoteList_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setItems(Arrays.asList());
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.cancel(result));
    }

    @Test
    void cancel_QuoteWithStateBasedProductWithQuoteItemsWithOutComponent_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.cancel(result));
    }








    @Test
    void modify_ValidQuote_ReturnsQuote() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        operations.create(result);
        Quote created = operations.getQuote();

        operations.modify(created);
        Quote modified = operations.getQuote();

        assertThat(modified).isNotNull();
        assertThat(modified.getClient().getId()).isNotNull();

        modified.getProducts()
                .stream()
                .map(StateBasedProduct::getId)
                .map(id ->
                        assertThat(id).isNotNull());

        modified.getProducts()
                .stream()
                .map(StateBasedProduct::getProduct)
                .map(Product::getId)
                .map(id ->
                        assertThat(id).isNotNull());

        modified.getProducts()
                .stream()
                .map(StateBasedProduct::getItems)
                .map(quoteItems -> quoteItems.stream().map(
                        QuoteItem::getId
                ))
                .map(id ->
                        assertThat(id).isNotNull());
    }


    @Test
    void modify_NullQuote_ThrowsQuoteMalformedException() {
        assertThrows(QuoteMalformedException.class, () -> operations.modify(null));
    }

    @Test
    void modify_QuoteWithNullClient_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(null);

        assertThrows(QuoteMalformedException.class, () -> operations.modify(result));
    }


    @Test
    void modify_QuoteWitEmptyProducts_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();


        result.setProducts(Arrays.asList());

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.modify(result));
    }

    @Test
    void modify_QuoteWithStateBasedProductWithOutProduct_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.modify(result));
    }

    @Test
    void modify_QuoteWithStateBasedProductWithEmptyQuoteList_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setItems(Arrays.asList());
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.modify(result));
    }

    @Test
    void modify_QuoteWithStateBasedProductWithQuoteItemsWithOutComponent_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.modify(result));
    }



    @Test
    void modify_QuoteisLastVersionOnList_ModifyResource() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        operations.create(result);
        Quote created = operations.getQuote();

        operations.modify(created);
        Quote modified = operations.getQuote();

    }

    @Test
    void modify_QuoteisNotLastVersionOnList_ModifyResource() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        operations.create(result);
        Quote created = operations.getQuote();

        operations.modify(created);

        assertThrows(QuoteVersionMismatchException.class, () -> operations.modify(created));

    }












    @Test
    void send_ValidQuote_ReturnsQuote() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        operations.create(result);
        Quote created = operations.getQuote();

        operations.send(created);
        Quote sent = operations.getQuote();

        assertThat(sent).isNotNull();
        assertThat(sent.getClient().getId()).isNotNull();

        sent.getProducts()
                .stream()
                .map(StateBasedProduct::getId)
                .map(id ->
                        assertThat(id).isNotNull());

        sent.getProducts()
                .stream()
                .map(StateBasedProduct::getProduct)
                .map(Product::getId)
                .map(id ->
                        assertThat(id).isNotNull());

        sent.getProducts()
                .stream()
                .map(StateBasedProduct::getItems)
                .map(quoteItems -> quoteItems.stream().map(
                        QuoteItem::getId
                ))
                .map(id ->
                        assertThat(id).isNotNull());
    }


    @Test
    void send_NullQuote_ThrowsQuoteMalformedException() {
        assertThrows(QuoteMalformedException.class, () -> operations.send(null));
    }

    @Test
    void send_QuoteWithNullClient_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(null);

        assertThrows(QuoteMalformedException.class, () -> operations.send(result));
    }


    @Test
    void send_QuoteWitEmptyProducts_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();


        result.setProducts(Arrays.asList());

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.send(result));
    }

    @Test
    void send_QuoteWithStateBasedProductWithOutProduct_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.send(result));
    }

    @Test
    void send_QuoteWithStateBasedProductWithEmptyQuoteList_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setItems(Arrays.asList());
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.send(result));
    }

    @Test
    void send_QuoteWithStateBasedProductWithQuoteItemsWithOutComponent_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.send(result));
    }



    @Test
    void send_QuoteisLastVersionOnList_ModifyResource() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        operations.create(result);
        Quote created = operations.getQuote();

        operations.send(created);
        Quote sent = operations.getQuote();

    }

    @Test
    void send_QuoteisNotLastVersionOnList_ModifyResource() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        operations.create(result);
        Quote created = operations.getQuote();

        operations.send(created);

        assertThrows(QuoteVersionMismatchException.class, () -> operations.send(created));

    }






    @Test
    void reject_ValidQuote_ReturnsQuote() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        operations.create(result);
        Quote created = operations.getQuote();

        operations.send(result);
        Quote sent = operations.getQuote();

        operations.reject(created);
        Quote rejected = operations.getQuote();

        assertThat(rejected).isNotNull();
        assertThat(rejected.getClient().getId()).isNotNull();

        rejected.getProducts()
                .stream()
                .map(StateBasedProduct::getId)
                .map(id ->
                        assertThat(id).isNotNull());

        rejected.getProducts()
                .stream()
                .map(StateBasedProduct::getProduct)
                .map(Product::getId)
                .map(id ->
                        assertThat(id).isNotNull());

        rejected.getProducts()
                .stream()
                .map(StateBasedProduct::getItems)
                .map(quoteItems -> quoteItems.stream().map(
                        QuoteItem::getId
                ))
                .map(id ->
                        assertThat(id).isNotNull());
    }


    @Test
    void reject_NullQuote_ThrowsQuoteMalformedException() {
        assertThrows(QuoteMalformedException.class, () -> operations.reject(null));
    }

    @Test
    void reject_QuoteWithNullClient_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(null);

        assertThrows(QuoteMalformedException.class, () -> operations.reject(result));
    }


    @Test
    void reject_QuoteWitEmptyProducts_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();


        result.setProducts(Arrays.asList());

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.reject(result));
    }

    @Test
    void reject_QuoteWithStateBasedProductWithOutProduct_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.reject(result));
    }

    @Test
    void reject_QuoteWithStateBasedProductWithEmptyQuoteList_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setItems(Arrays.asList());
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.reject(result));
    }

    @Test
    void reject_QuoteWithStateBasedProductWithQuoteItemsWithOutComponent_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.reject(result));
    }



    @Test
    void reject_QuoteisLastVersionOnList_ModifyResource() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        operations.create(result);
        Quote created = operations.getQuote();

        operations.reject(created);
        Quote modified = operations.getQuote();

    }

    @Test
    void reject_QuoteisNotLastVersionOnList_ModifyResource() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        operations.create(result);
        Quote created = operations.getQuote();

        operations.reject(created);

        assertThrows(QuoteVersionMismatchException.class, () -> operations.reject(created));

    }

    @Test
    void accept_ValidQuote_ReturnsQuote() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        operations.create(result);
        Quote created = operations.getQuote();

        operations.accept(created);
        Quote accepted = operations.getQuote();

        assertThat(accepted).isNotNull();
        assertThat(accepted.getClient().getId()).isNotNull();

        accepted.getProducts()
                .stream()
                .map(StateBasedProduct::getId)
                .map(id ->
                        assertThat(id).isNotNull());

        accepted.getProducts()
                .stream()
                .map(StateBasedProduct::getProduct)
                .map(Product::getId)
                .map(id ->
                        assertThat(id).isNotNull());

        accepted.getProducts()
                .stream()
                .map(StateBasedProduct::getItems)
                .map(quoteItems -> quoteItems.stream().map(
                        QuoteItem::getId
                ))
                .map(id ->
                        assertThat(id).isNotNull());
    }


    @Test
    void accept_NullQuote_ThrowsQuoteMalformedException() {
        assertThrows(QuoteMalformedException.class, () -> operations.accept(null));
    }

    @Test
    void accept_QuoteWithNullClient_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(null);

        assertThrows(QuoteMalformedException.class, () -> operations.accept(result));
    }


    @Test
    void accept_QuoteWitEmptyProducts_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();


        result.setProducts(Arrays.asList());

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.accept(result));
    }

    @Test
    void accept_QuoteWithStateBasedProductWithOutProduct_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.accept(result));
    }

    @Test
    void accept_QuoteWithStateBasedProductWithEmptyQuoteList_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setItems(Arrays.asList());
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.accept(result));
    }

    @Test
    void accept_QuoteWithStateBasedProductWithQuoteItemsWithOutComponent_ThrowsQuoteMalformedException() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        result.setClient(client);

        assertThrows(QuoteMalformedException.class, () -> operations.accept(result));
    }



    @Test
    void accept_QuoteisLastVersionOnList_ModifyResource() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        operations.create(result);
        Quote created = operations.getQuote();

        operations.accept(created);
        Quote accepted = operations.getQuote();

    }

    @Test
    void accept_QuoteisNotLastVersionOnList_ModifyResource() {
        Quote quote = QuoteFactory.makeAskQuote();
        Client client = new Client();
        quote.setClient(clientRepository.save(client));
        operations.request(quote);
        Quote result = operations.getQuote();

        Product product = new Product();
        QuoteItem quoteItem = new QuoteItem();

        StateBasedProduct stateBasedProduct = new StateBasedProduct();
        stateBasedProduct.setItems(Arrays.asList(quoteItem));
        stateBasedProduct.setProduct(product);
        stateBasedProduct.setState(ProductState.NEW);

        result.setProducts(Arrays.asList(stateBasedProduct));

        operations.create(result);
        Quote created = operations.getQuote();

        operations.accept(created);

        assertThrows(QuoteVersionMismatchException.class, () -> operations.accept(created));

    }
}
