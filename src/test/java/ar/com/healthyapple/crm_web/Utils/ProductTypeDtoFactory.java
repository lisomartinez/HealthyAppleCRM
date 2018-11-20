package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.dto.Product.ProductTypeDto;

public class ProductTypeDtoFactory {
    public static ProductTypeDto makeProductTypeDto() {
        ProductTypeDto productTypeDto = new ProductTypeDto();
        productTypeDto.setId(1L);
        productTypeDto.setName("name");
        return productTypeDto;
    }
}
