package ar.com.healthyapple.crm_web.controller;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MemoryControllerTest.class,
        MotherBoardControllerTest.class,
        PcCaseControllerTest.class,
        HardDriveControllerTest.class,
        ProcessorControllerTest.class,
        EntityDtoConverterTest.class,
        ComputerControllerTest.class
})
public class AllControllerTests {
}
