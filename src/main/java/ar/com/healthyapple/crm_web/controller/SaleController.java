package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.controller.DtoConverter.SaleDtoConverter;
import ar.com.healthyapple.crm_web.dto.Sale.SaleDto;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Sale.Sale;
import ar.com.healthyapple.crm_web.service.Sale.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Uris.SALES)
public class SaleController {

    private SaleService saleService;

    private SaleDtoConverter saleDtoConverter;

    @Autowired
    public SaleController(SaleService saleService, SaleDtoConverter saleDtoConverter) {
        this.saleService = saleService;
        this.saleDtoConverter = saleDtoConverter;
    }

    @RequestMapping(Uris.FINISH)
    @ResponseStatus(HttpStatus.OK)
    public SaleDto finishSale(@RequestBody SaleDto saleDto) {
        Sale sale = saleService.finish(saleDtoConverter.convertToEntity(saleDto));
        return saleDtoConverter.convertToDto(sale);
    }


    SaleDto createFromQuote(Quote quote) {
        Sale sale = saleService.createFromQuote(quote);
        return saleDtoConverter.convertToDto(sale);
    }


}
