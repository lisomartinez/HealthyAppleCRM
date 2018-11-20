package ar.com.healthyapple.crm_web.repository.Client;

import ar.com.healthyapple.crm_web.repository.Product.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClientRepositoryTest.class,
        ClientCustomRepositoryImplTest.class
})
public class AllClientRepositoryTests {
}
