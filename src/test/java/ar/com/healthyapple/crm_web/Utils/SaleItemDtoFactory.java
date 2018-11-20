package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.dto.Sale.SaleItemDto;
import ar.com.healthyapple.crm_web.model.Sale.SaleItem;

public class SaleItemDtoFactory {
    public static SaleItemDto makeSaleItemDto() {
        SaleItemDto saleItemDto = new SaleItemDto();
        return saleItemDto;
    }
}
