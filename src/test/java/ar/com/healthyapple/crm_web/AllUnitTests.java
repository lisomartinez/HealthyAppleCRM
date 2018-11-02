package ar.com.healthyapple.crm_web;

import ar.com.healthyapple.crm_web.controller.AllControllerTests;
import ar.com.healthyapple.crm_web.model.Computer.AllComputerTests;
import ar.com.healthyapple.crm_web.service.Computer.AllServiceTest;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;


@RunWith(JUnitPlatform.class)
@SelectClasses({
        AllControllerTests.class,
        AllComputerTests.class,
        AllServiceTest.class
})
public class AllUnitTests {
}
