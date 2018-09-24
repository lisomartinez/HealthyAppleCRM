package ar.com.healthyapple.crm_web.service.Computer;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        HardDiskServiceImplTest.class,
        MemoryServiceImplTest.class,
        MotherBoardServiceTest.class,
        PcCaseServiceImplTest.class,
        PowerSupplyServiceImplTest.class,
        ProcessorServiceImplTest.class,
        TechnicalSpecificationServiceImplTest.class
})
public class AllServiceTest {
}
