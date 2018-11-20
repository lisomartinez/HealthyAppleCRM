package ar.com.healthyapple.crm_web.service.Sale;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Quote.QuoteItem;
import ar.com.healthyapple.crm_web.model.Sale.SaleItem;
import ar.com.healthyapple.crm_web.repository.Sale.SaleItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SaleItemServiceImpl implements SaleItemService {

    private SaleItemRepository saleItemRepository;

    @Autowired
    public SaleItemServiceImpl(SaleItemRepository saleItemRepository) {
        this.saleItemRepository = saleItemRepository;
    }

    @Override
    @Transactional
    public SaleItem create(SaleItem productServiceSale) throws AlreadyExistException {
//        if (serviceRepository.findByProductAndAndName(productServiceSale.getProduct(), productServiceSale.getName()).isPresent()) {
//            throw new AlreadyExistException("QuoteItemDto already exists");
//        }
        return saleItemRepository.save(productServiceSale);
    }

    @Override
    public SaleItem read(Long id) throws NotFoundException {
        return saleItemRepository.findById(id).orElseThrow(() -> new NotFoundException("QuoteItemDto not found"));
    }

    @Override
    @Transactional
    public SaleItem update(SaleItem productServiceSale) throws NotFoundException {
        saleItemRepository.findById(productServiceSale.getId()).orElseThrow(() -> new NotFoundException("QuoteItemDto not found"));
        return saleItemRepository.save(productServiceSale);
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws NotFoundException {
        saleItemRepository.findById(id).orElseThrow(() -> new NotFoundException("QuoteItemDto not found"));
        saleItemRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(SaleItem productServiceSale) throws NotFoundException {
        saleItemRepository.findById(productServiceSale.getId()).orElseThrow(() -> new NotFoundException("QuoteItemDto not found"));
        saleItemRepository.delete(productServiceSale);
    }

    @Override
    public SaleItem createFromQuoteItem(QuoteItem quoteItem) {
        SaleItem newSaleItem = new SaleItem();
        newSaleItem.setDescription(quoteItem.getDescription());
        newSaleItem.setComponent(quoteItem.getComponent());
        newSaleItem.setCost(quoteItem.getCost());
        newSaleItem.setPrice(quoteItem.getPrice());
        return saleItemRepository.save(newSaleItem);
    }
}
