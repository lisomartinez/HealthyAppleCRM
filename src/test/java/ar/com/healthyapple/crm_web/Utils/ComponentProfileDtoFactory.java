package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.dto.Product.ComponentProfileDto;
import ar.com.healthyapple.crm_web.model.Product.ComponentProfile;

import java.util.HashMap;
import java.util.Map;

public class ComponentProfileDtoFactory {
    public static ComponentProfileDto makeComponentProfileDto() {
        ComponentProfileDto componentProfileDto = new ComponentProfileDto();
        componentProfileDto.setId(1L);
        componentProfileDto.setType("Type");
        componentProfileDto.setDescription("Description");
        componentProfileDto.setMultiple(true);

        Map<String, String> specifications = new HashMap<>();
        specifications.put("spec1", "spec1");
        componentProfileDto.setSpecifications(specifications);

        return componentProfileDto;
        
    }
}
