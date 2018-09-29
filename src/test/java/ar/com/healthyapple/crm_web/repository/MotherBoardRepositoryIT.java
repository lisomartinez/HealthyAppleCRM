package ar.com.healthyapple.crm_web.repository;

import ar.com.healthyapple.crm_web.model.Computer.MotherBoard;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MotherBoardRepositoryIT  {
//
//    @Autowired
//    private MotherBoardRepository motherBoardRepository;
//
//    private List <MotherBoard> mbsList;
//
//    @Before
//    public void setUp() {
//
//        mbsList = new ArrayList<>();
//
//        MotherBoard mb1 = new MotherBoard("brand1", "AAA", "model1", "socket1");
//        MotherBoard mb2 = new MotherBoard("brand2", "AAA", "model2", "socket2");
//        MotherBoard mb3 = new MotherBoard("brand3", "AAA", "model3", "socket3");
//        MotherBoard mb4 = new MotherBoard("brand4", "AAA", "model4", "socket4");
//
//        mbsList.add(mb1);
//        mbsList.add(mb2);
//        mbsList.add(mb3);
//        mbsList.add(mb4);
//
//        motherBoardRepository.save(mb1);
//        motherBoardRepository.save(mb2);
//        motherBoardRepository.save(mb3);
//        motherBoardRepository.save(mb4);
//    }
//
//    @After
//    public void tearDown() {
//        motherBoardRepository.deleteAll();
//    }
//
//    @Test
//    public void testCreate() {
//        assertEquals(4, motherBoardRepository.count());
//    }
//
//    @Test
//    public void testFindAll() {
//        List<MotherBoard> mbs = motherBoardRepository.findAll();
//        assertEquals(mbs, mbsList);
//    }
//
//    //TODO: modify MotherboardDao
//    @Test
//    public void testFindByBrand(){
////        List<MotherBoard> mbs = motherBoardDao.findByBrand("brand1");
////        assertThat(mbs.get(0).getBrand(), equalTo("brand1") );
//    }
//
//    //TODO: modify MotherboardDao
//    @Test
//    public void testFindByModel() {
////        List<MotherBoard> mbs = motherBoardDao.findByModel("model1");
////        assertThat(mbs.get(0).getModel(), equalTo("model1") );
//    }
//
//    //TODO: modify MotherboardDao
//    @Test
//    @Ignore
//    public void testFindByBrandAndModel() {
////        MotherBoard mbs = motherBoardDao.findByBrandAndModel("brand1","model1");
////        assertThat(mbs.getBrand(), equalTo("brand1"));
////        assertThat(mbs.getModel(), equalTo("model1"));
//    }
}