package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.dto.Product.ComponentTypeDto;
import ar.com.healthyapple.crm_web.model.Product.ComponentType;

public class ComponentTypeDtoFactory {
    public static ComponentTypeDto makeComponentTypeDto() {
        ComponentTypeDto componentTypeDto = new ComponentTypeDto();
        componentTypeDto.setId(1L);
        componentTypeDto.setName("name");
        return componentTypeDto;
    }
}
