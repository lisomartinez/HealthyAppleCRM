package ar.com.healthyapple.crm_web.repository;

import ar.com.healthyapple.crm_web.repository.Client.AllClientRepositoryTests;
import ar.com.healthyapple.crm_web.repository.Product.AllProductRepositoryTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllClientRepositoryTests.class,
        AllProductRepositoryTests.class
})
public class AllDaosTests {
}
