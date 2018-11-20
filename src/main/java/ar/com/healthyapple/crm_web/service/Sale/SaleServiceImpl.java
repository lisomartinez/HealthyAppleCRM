package ar.com.healthyapple.crm_web.service.Sale;

import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Product.StateBasedProduct;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Sale.Sale;
import ar.com.healthyapple.crm_web.model.Sale.SaleItem;
import ar.com.healthyapple.crm_web.model.Sale.SaleState;
import ar.com.healthyapple.crm_web.repository.Sale.SaleRepository;
import ar.com.healthyapple.crm_web.service.Client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private SaleRepository saleRepository;

    private SaleItemService saleItemService;

    private ClientService clientService;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, SaleItemService saleItemService) {
        this.saleRepository = saleRepository;
        this.saleItemService = saleItemService;
    }

    @Override
    //TODO: REFACTOR
    public Sale create(Sale sale) {
            return saleRepository.save(sale);
    }

    @Override
    public Sale read(Long id) throws NotFoundException {
        return saleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sale do not exist"));
    }

    @Override
    @Transactional
    public Sale update(Sale sale) throws NotFoundException {
        saleRepository.findById(sale.getId()).orElseThrow(() -> new NotFoundException("Sale do not exist"));
            return saleRepository.save(sale);

    }

    @Override
    @Transactional
    public void deleteById(Long id) throws NotFoundException {
        saleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sale do not exist"));
        saleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(Sale sale) throws NotFoundException {
        saleRepository.findById(sale.getId())
                .orElseThrow(() -> new NotFoundException("Sale do not exist"));
        saleRepository.delete(sale);
    }

    @Override
    public Sale createFromQuote(Quote quote) {
        Sale newSale = new Sale();
        newSale.setDescription(quote.getDescription());
        newSale.setState(SaleState.IN_PROGRESS);
        newSale.setStartDate(LocalDate.now());

        newSale.setQuote(quote);

        return saleRepository.save(newSale);
    }

    @Override
    @Transactional
    public Sale closeSale(Long id) throws NotFoundException {
        Sale sale = saleRepository.findById(id).orElseThrow(() -> new NotFoundException("Sale does not exist"));
        sale.setState(SaleState.FINISHED);
        return saleRepository.save(sale);
    }

    @Override
    public Sale cancelSale(Long id) throws NotFoundException {
        Sale sale = saleRepository.findById(id).orElseThrow(() -> new NotFoundException("Sale does not exist"));
        sale.setState(SaleState.CANCELED);
        return saleRepository.save(sale);
    }

    @Override
    public Sale finish(Sale sale) {
        Sale retriedSale = saleRepository.findById(sale.getId()).orElseThrow(() -> new NotFoundException("Sale does not exist"));
        retriedSale.setState(SaleState.FINISHED);
        Long clientId = retriedSale.getQuote().getClient().getId();

        retriedSale.getQuote().getProducts()
                .stream()
                .map(StateBasedProduct::getProduct)
                .map(product -> clientService.updateProduct(clientId, product));

        return saleRepository.save(sale);
    }

}
