package ar.com.healthyapple.crm_web;

import ar.com.healthyapple.crm_web.controller.AllControllerTests;
import ar.com.healthyapple.crm_web.model.Computer.AllComputerTests;
import ar.com.healthyapple.crm_web.service.Computer.AllServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllControllerTests.class,
        AllComputerTests.class,
        AllServiceTest.class
})
public class AllUnitTests {
}
