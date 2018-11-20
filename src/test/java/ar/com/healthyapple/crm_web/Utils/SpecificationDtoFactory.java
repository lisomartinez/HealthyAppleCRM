package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.dto.Product.SpecificationDto;

public class SpecificationDtoFactory {
    public static SpecificationDto makeSpecificationDto() {
        SpecificationDto specificationDto = new SpecificationDto();
        specificationDto.setId(1L);
        specificationDto.setName("name");
        specificationDto.setDescription("description");
        return specificationDto;
    }
}
