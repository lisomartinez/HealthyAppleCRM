package ar.com.healthyapple.crm_web.repository;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class HardDriveRepositoryIT {
//
//    private HardDrive hd;
//
//
//    @Autowired
//    private HardDriveRepository hardDriveRepository;
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Before
//    public void setUp() {
//
//         hd = new HardDrive("AAA", "brand", "model", "type", "size");
//         entityManager.persist(hd);
//         entityManager.flush();
//    }
//
//    @After
//    public void tearDown() {
//
//    }
//
//    @Test
//    public void test1() {
//        assertEquals(1, hardDriveRepository.count());
//    }
//
//    @Test
//    @Ignore
//    public void testFindbyBrand() {
////        HardDrive hd = hardDriveDao.findHardDriveByBrand("brand").orElse(new HardDrive());
////        assertThat(hd.getBrand(), equalTo("brand"));
//    }
}