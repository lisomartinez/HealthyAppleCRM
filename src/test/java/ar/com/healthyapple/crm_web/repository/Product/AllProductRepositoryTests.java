package ar.com.healthyapple.crm_web.repository.Product;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ComponentProfileRepositoryTest.class,
        ComponentRepositoryTest.class,
        ComponentTypeRepositoryTest.class,
        ProductProfileRepositoryTest.class,
        ProductRepositoryTest.class,
        ProductTypeRepositoryTest.class,
        SpecificationRepositoryTest.class
})
public class AllProductRepositoryTests {
}
