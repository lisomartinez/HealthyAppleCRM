package ar.com.healthyapple.crm_web.service.Sale;

import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Sale.Sale;
import ar.com.healthyapple.crm_web.repository.Service.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SaleServiceImpl implements SaleService {

    private SaleRepository saleRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
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
}
