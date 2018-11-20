package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.dto.Product.ProductDto;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductDtoFactory {
    public static ProductDto makeProductDto() {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setProductType(ProductTypeDtoFactory.makeProductTypeDto());
        productDto.setDescription("description");
        productDto.setComponents(new ArrayList<>(Arrays.asList(ComponentDtoFactory.makeComponentDto())));
        return productDto;
    }
}
