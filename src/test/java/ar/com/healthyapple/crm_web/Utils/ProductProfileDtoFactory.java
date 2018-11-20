package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.dto.Product.ProductProfileDto;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductProfileDtoFactory {
    public static ProductProfileDto makeProductProfileDto() {
        ProductProfileDto productProfileDto = new ProductProfileDto();

        productProfileDto.setId(1L);
        productProfileDto.setDescription("description");
        productProfileDto.setType("type");
        productProfileDto.setComponents(new ArrayList<>(Arrays.asList(ComponentProfileDtoFactory.makeComponentProfileDto())));
        return productProfileDto;    }
}
