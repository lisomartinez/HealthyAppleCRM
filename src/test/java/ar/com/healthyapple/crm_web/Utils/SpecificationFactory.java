package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.model.Product.Specification;

public class SpecificationFactory {

    public static Specification makeSpecification() {
        Specification specification = new Specification();
        specification.setId(1L);
        specification.setName("name");
        specification.setDescription("description");
        return specification;
    }

    public static Specification makeEmptySpecification() {
        return new Specification();
    }
}
