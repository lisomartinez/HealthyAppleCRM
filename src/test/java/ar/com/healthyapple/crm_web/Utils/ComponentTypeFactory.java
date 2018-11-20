package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.model.Product.ComponentType;

public class ComponentTypeFactory {
    public static ComponentType makeComponentType() {
        ComponentType componentType = new ComponentType();
        componentType.setId(1L);
        componentType.setName("name");
        return componentType;
    }

    public static ComponentType makeEmptyComponentType() {
        return new ComponentType();
    }
}
