package ar.com.healthyapple.crm_web.controller;


import static ar.com.healthyapple.crm_web.controller.Uris.HARD_DRIVES;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ar.com.healthyapple.crm_web.dto.Computer.HardDriveDto;
import ar.com.healthyapple.crm_web.repository.Computer.HardDriveRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HardDriveControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HardDriveRepository hardDriveRepository;

    @Autowired
    ObjectMapper objectMapper;


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getHarDriveByIdShouldReturnDto() throws Exception {
        HardDriveDto hd = new HardDriveDto("AAA","Intel", "casio", "ssd", "444");
        MvcResult result = this.mockMvc.perform(post(HARD_DRIVES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(hd)))
                .andExpect(status().isCreated()).andReturn();
        String body = result.getResponse().getContentAsString();
        hd = objectMapper.readValue(body, HardDriveDto.class);
        MvcResult res = this.mockMvc.perform(get(HARD_DRIVES + "/{id}", hd.getId()))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        String bodyres = result.getResponse().getContentAsString();
        assertThat(bodyres).isEqualTo(hd.toString());
    }


    @Test
    public void createShouldReturnHttpCreated() throws Exception {
        this.mockMvc.perform(post(HARD_DRIVES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HardDriveDto("Intel2234535", "casio22345352", "AAA","ssd223453452", "444345345222"))))
                .andExpect(status().isCreated()).andDo(print());
    }

    @Test
    public void createDuplicateShouldReturnAlreadyExistException() throws Exception {

        this.mockMvc.perform(post(HARD_DRIVES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HardDriveDto("Intel222342423234535", "casio2223424345352", "AAA","ssd2234523423452", "44432342445345222"))))
                .andExpect(status().isCreated());

        this.mockMvc.perform(post(HARD_DRIVES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HardDriveDto("Intel222342423234535", "casio2223424345352","AAA", "ssd2234523423452", "44432342445345222"))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void findAllShouldReturnListOfHardDiskDtos() throws Exception {
        List<HardDriveDto> hardDriveDtoList;

        hardDriveDtoList = new ArrayList<>(5);
        hardDriveDtoList.add(new HardDriveDto("Camel", "box", "AAA","fiex", "4123"));
        hardDriveDtoList.add(new HardDriveDto("Samsung", "850 EVO","AAA", "ssd", "250"));
        hardDriveDtoList.add(new HardDriveDto("Wd", "Barracuda", "AAA","solid", "47456"));

        hardDriveDtoList.forEach(hd -> {
            try {
                this.mockMvc.perform(post(HARD_DRIVES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hd)))
                        .andExpect(status().isCreated());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        IntStream.range(0, hardDriveDtoList.size()).forEach(index -> hardDriveDtoList.get(index).setId(index + 1L));

        this.mockMvc.perform(get(HARD_DRIVES))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(hardDriveDtoList.size() + 1)));
    }

    @Test
    public void FindByBrandAndModelShouldReturnDto() throws Exception {


        HardDriveDto hd = new HardDriveDto("BrandBefore", "modelBefore", "AAA","typeBefore", "sizeBefore");

        this.mockMvc.perform(post(HARD_DRIVES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(hd)))
                .andExpect(status().isCreated()).andDo(print());


        MvcResult result = this.mockMvc.perform(get(Uris.HARD_DRIVES + Uris.SEARCH)
                .param("brand", hd.getBrand())
                .param("model", hd.getModel()))
                .andExpect(status().isOk()).andReturn();

        String body = result.getResponse().getContentAsString();

        assertThat(body).contains(hd.getBrand()).contains(hd.getModel());

    }

    @Test
    public void GivenAHardDriveDtoWhenPutUpdateShouldReturnUpdatedDto() throws Exception {

        //Given: a HardDrive in the database
        HardDriveDto hd = new HardDriveDto("BrandBeforeUpdate", "modelBeforeUpdate", "AAA","typeBeforeUpdate", "sizeBeforeUpdate");

        MvcResult result = this.mockMvc.perform(post(HARD_DRIVES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(hd)))
                .andExpect(status().isCreated()).andReturn();

        String body = result.getResponse().getContentAsString();


        //When it is updated

        hd = objectMapper.readValue(body, HardDriveDto.class);
        hd.setBrand("AfterUpdate");
        hd.setModel("AfterUpdate");

        MvcResult res = this.mockMvc.perform(put(HARD_DRIVES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(hd)))
                .andExpect(status().isOk()).andReturn();


        //Then the updated harddriveDto is returned
        String bodyres = res.getResponse().getContentAsString();

        assertThat(bodyres).isEqualTo(hd.toString());
    }

    @Test
    public void putNonExistentHardDriveShouldReturnNotFound() throws Exception {
        this.mockMvc.perform(put(HARD_DRIVES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HardDriveDto(156564564564l, "toPutBrand", "AAA","toPutModel", "toPutType", "toPutSize"))))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteByIdShouldReturnOk() throws Exception {
        HardDriveDto hd = new HardDriveDto("toDeleteOKBrand", "toDeleteOKModel", "AAA","toDeleteOKType", "toDeleteOKSize");

        MvcResult result = this.mockMvc.perform(post(HARD_DRIVES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(hd)))
                .andExpect(status().isCreated()).andReturn();

        String body = result.getResponse().getContentAsString();
        hd = objectMapper.readValue(body, HardDriveDto.class);


        this.mockMvc.perform(delete(HARD_DRIVES + Uris.ID, hd.getId())).andExpect(status().isOk());
    }

    @Test
    public void deleteByNoExistingIdShouldTrowNotFoundException() throws Exception {

        this.mockMvc.perform(delete(HARD_DRIVES + Uris.ID, 100000000L)).andExpect(status().isNotFound()).andDo(print());
    }

    @Test
    public void deleteByResourceShouldReturnOk() throws Exception {
        HardDriveDto hd = new HardDriveDto("toDeleteBrand", "toDeleteModel", "AAA","toDeleteType", "toDeleteSize");

        MvcResult result = this.mockMvc.perform(post(HARD_DRIVES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(hd)))
                .andExpect(status().isCreated()).andReturn();

        String body = result.getResponse().getContentAsString();
        hd = objectMapper.readValue(body, HardDriveDto.class);

        this.mockMvc.perform(delete(HARD_DRIVES).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(hd))).andExpect(status().isOk());
    }

    @Test
    public void deleteByNoExistingHardDriveDtoShouldTrowNotFoundException() throws Exception {
        HardDriveDto hd = new HardDriveDto(111L,"toDeleteNonExistentHardDriveBrand", "AAA","toDeleteNonExistentHardDriveModel", "toDeleteNonExistentHardDriveType", "toDeleteNonExistentHardDriveSize");

        this.mockMvc.perform(delete(HARD_DRIVES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(hd)))
                .andExpect(status().isNotFound());
    }
}
