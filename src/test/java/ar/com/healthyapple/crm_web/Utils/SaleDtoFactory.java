package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.dto.Sale.SaleDto;
import ar.com.healthyapple.crm_web.model.Sale.SaleState;

public class SaleDtoFactory {
    public static SaleDto makeSaleDto() {
        SaleDto saleDto = new SaleDto();
        saleDto.setId(1L);
        saleDto.setDescription("description");
        saleDto.setState(SaleState.IN_PROGRESS);
        saleDto.setStartDate("2010-09-11");
        saleDto.setQuote(QuoteDtoFactory.makeQuoteDto());
        return saleDto;
    }
}
