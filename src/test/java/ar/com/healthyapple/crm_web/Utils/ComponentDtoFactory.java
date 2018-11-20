package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.dto.Product.ComponentDto;

import java.util.ArrayList;
import java.util.Arrays;

public class ComponentDtoFactory {
    public static ComponentDto makeComponentDto() {
        ComponentDto componentDto = new ComponentDto();
        componentDto.setId(1L);
        componentDto.setComponentType(ComponentTypeDtoFactory.makeComponentTypeDto());
        componentDto.setSpecifications(new ArrayList<>(Arrays.asList(SpecificationDtoFactory.makeSpecificationDto())));
        return componentDto;
    }
}
