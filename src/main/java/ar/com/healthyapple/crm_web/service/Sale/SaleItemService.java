package ar.com.healthyapple.crm_web.service.Sale;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Quote.QuoteItem;
import ar.com.healthyapple.crm_web.model.Sale.SaleItem;
import org.springframework.stereotype.Service;

@Service
public interface SaleItemService {
    SaleItem create(SaleItem productServiceSale) throws AlreadyExistException;
    SaleItem read(Long id) throws NotFoundException;
    SaleItem update(SaleItem productServiceSale) throws NotFoundException;
    void deleteById(Long id) throws NotFoundException;
    void delete(SaleItem productServiceSale) throws NotFoundException;
    SaleItem createFromQuoteItem(QuoteItem quoteItem);
}
