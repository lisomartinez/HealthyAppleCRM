package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.model.Product.Component;
import ar.com.healthyapple.crm_web.model.Product.Specification;

import java.util.ArrayList;
import java.util.Arrays;

public class ComponentFactory {
    public static Component makeComponent() {
        Component component = new Component();
        component.setId(1L);
        component.setComponentType(ComponentTypeFactory.makeComponentType());
        component.setSpecifications(new ArrayList<>(Arrays.asList(SpecificationFactory.makeSpecification())));
        return component;
    }

    public static Component makeEmptyComponent() {
        return new Component();
    }
}
