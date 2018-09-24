package ar.com.healthyapple.crm_web.repository;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        HardDriveRepositoryIT.class,
        MotherBoardRepositoryIT.class
})
public class AllDaosTests {
}
