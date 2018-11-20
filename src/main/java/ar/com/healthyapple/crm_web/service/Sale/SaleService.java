package ar.com.healthyapple.crm_web.service.Sale;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Sale.Sale;
import org.springframework.stereotype.Service;

@Service
public interface SaleService {

    Sale create(Sale sale) throws AlreadyExistException;
    Sale read(Long id) throws NotFoundException;
    Sale update(Sale sale) throws NotFoundException;
    void deleteById(Long id) throws NotFoundException;
    void delete(Sale sale) throws NotFoundException;
    Sale createFromQuote(Quote quote);
    Sale closeSale(Long id) throws NotFoundException;
    Sale cancelSale(Long id) throws NotFoundException;
    Sale finish(Sale sale);
}
