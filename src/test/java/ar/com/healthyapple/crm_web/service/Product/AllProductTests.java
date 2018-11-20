package ar.com.healthyapple.crm_web.service.Product;

import ar.com.healthyapple.crm_web.model.Product.ProductProfile;
import ar.com.healthyapple.crm_web.model.Product.ProductType;
import ar.com.healthyapple.crm_web.model.Product.Specification;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ComponentServiceTest.class,
    ComponentProfileServiceTest.class,
    ComponentTypeServiceTest.class,
    ProductProfileServiceTest.class,
    ProductServiceTest.class,
    ProductTypeServicelTest.class,
    SpecificationServicelTest.class
})
public class AllProductTests {
}
