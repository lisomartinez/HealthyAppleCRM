package ar.com.healthyapple.crm_web.repository.Computer;

import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Computer.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ComputerRepositoryTest {

  /*  @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ComputerDao computerDao;

    private HackintoshComputer hackintoshComputer;

    private MacComputer macComputer;

    private final String MODEL_IDENTIFIER = "AAA1234";
    private final String SERIAL_NUMBER = "123";
    private final String PREINSTALLED_MACOS = "Mavericks";
    private final Double DISPLAY_SIZE = 15.0;

    @Before
    public void setUp() throws Exception {
        macComputer = new MacComputer(
                new MotherBoard(),
                new Processor(),
                new Memory(),
                new HardDrive(),
                MODEL_IDENTIFIER,
                SERIAL_NUMBER,
                LocalDate.of(2010,8,4),
                PREINSTALLED_MACOS,
                DISPLAY_SIZE
        );

        hackintoshComputer = new HackintoshComputer(
                new MotherBoard(),
                new Processor(),
                new Memory(),
                new HardDrive(),
                new PcCase(),
                new PowerSupply(),
                new VideoCard()
        );


        testEntityManager.persist(macComputer);
        testEntityManager.persist(hackintoshComputer);


        testEntityManager.flush();
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void create() {
        MacComputer mac1 = new MacComputer(
                new MotherBoard("Gigabyte", "B85M","AAA","775"),
                new Processor("Intel", "i3","AAA", 4, "775", 1000),
                new Memory("Kingston", "Detonator", "AAA","DDR4",1600, 4),
                new HardDrive("WD", "caviar black", "AAA","ssd", "5000"),
                MODEL_IDENTIFIER,
                SERIAL_NUMBER,
                LocalDate.of(2010,8,4),
                PREINSTALLED_MACOS,
                DISPLAY_SIZE
        );

        computerDao.save(mac1);
    }

    @Test
    public void read() throws Exception {
        Long id = (Long) testEntityManager.getId(macComputer);
        Computer computer = computerDao.findById(id)
                .orElseThrow(NotFoundException::new);
        assertThat(computer).isEqualTo(macComputer);
    }

    @Test
    public void findAll() throws Exception {
        List<Computer> computers = computerDao.findAll();
        List<Computer> source = Arrays.asList(macComputer, hackintoshComputer);
        assertThat(computers).isEqualTo(source);
    }*/
}