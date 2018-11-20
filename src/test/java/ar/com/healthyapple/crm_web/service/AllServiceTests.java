package ar.com.healthyapple.crm_web.service;

import ar.com.healthyapple.crm_web.service.Client.AllClientTests;
import ar.com.healthyapple.crm_web.service.Product.AllProductTests;
import ar.com.healthyapple.crm_web.service.Quote.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  AllQuoteTest.class,
  AllProductTests.class,
  AllClientTests.class
})
public class AllServiceTests {
}
