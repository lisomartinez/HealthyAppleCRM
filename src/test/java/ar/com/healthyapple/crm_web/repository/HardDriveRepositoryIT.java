package ar.com.healthyapple.crm_web.repository;

import ar.com.healthyapple.crm_web.model.Computer.HardDrive;


import ar.com.healthyapple.crm_web.repository.Computer.HardDriveRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
public class HardDriveRepositoryIT extends Computer {

    private HardDrive hd;


    @Autowired
    private HardDriveRepository hardDriveRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Before
    public void setUp() {

         hd = new HardDrive("AAA", "brand", "model", "type", "size");
         entityManager.persist(hd);
         entityManager.flush();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void test1() {
        assertEquals(1, hardDriveRepository.count());
    }

    @Test
    @Ignore
    public void testFindbyBrand() {
//        HardDrive hd = hardDriveDao.findHardDriveByBrand("brand").orElse(new HardDrive());
//        assertThat(hd.getBrand(), equalTo("brand"));
    }
}