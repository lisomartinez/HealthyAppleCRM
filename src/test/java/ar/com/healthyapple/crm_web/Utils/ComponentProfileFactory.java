package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.model.Product.ComponentProfile;

import java.util.HashMap;
import java.util.Map;

public class ComponentProfileFactory {
    public static ComponentProfile makeComponentProfile() {
        ComponentProfile componentProfile = new ComponentProfile();
        componentProfile.setId(1L);
        componentProfile.setType("Type");
        componentProfile.setDescription("Description");
        componentProfile.setMultiple(true);

        Map<String, String> specifications = new HashMap<>();
        specifications.put("spec1", "spec1");
        componentProfile.setSpecifications(specifications);

        return componentProfile;
    }
}
