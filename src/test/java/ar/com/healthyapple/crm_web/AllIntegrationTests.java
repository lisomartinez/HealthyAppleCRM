package ar.com.healthyapple.crm_web;

import ar.com.healthyapple.crm_web.controller.AllControllerIntegrationTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllControllerIntegrationTests.class
})

public class AllIntegrationTests {
}
