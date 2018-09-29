package ar.com.healthyapple.crm_web.controller;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MemoryControllerIT {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//
//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//    @Test
//    public void createShouldReturnOk() throws Exception{
//        MemoryDto mem = new MemoryDto("Kingston", "Saphire", "AAA","DDR4", 500, 8);
//        MvcResult result = this.mockMvc.perform(post(Uris.MEMORY)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(mem)))
//                .andExpect(status().isCreated())
//                .andReturn();
//        String body = result.getResponse().getContentAsString();
//        MemoryDto memResult = objectMapper.readValue(body, MemoryDto.class);
//        assertThat(memResult.getBrand()).isEqualTo(mem.getBrand());
//        assertThat(memResult.getModel()).isEqualTo(mem.getModel());
//        assertThat(memResult.getSpeed()).isEqualTo(mem.getSpeed());
//
//    }
//
//    @Test
//    public void createDuplicateShouldReturnAlreadyExistsException() throws Exception {
//        MemoryDto mem = new MemoryDto("ToDuplicate", "ToDuplicate", "AAA","DDR4",500, 8);
//        this.mockMvc.perform(post(Uris.MEMORY)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(mem)))
//                .andExpect(status().isCreated());
//        this.mockMvc.perform(post(Uris.MEMORY)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(mem)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void updateShouldReturnOkAndResource() throws Exception {
//        MemoryDto mem = new MemoryDto("Kingston", "Saphire", "AAA","DDR4", 500, 8);
//        MvcResult result = this.mockMvc.perform(post(Uris.MEMORY)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(mem)))
//                .andExpect(status().isCreated())
//                .andReturn();
//        String body = result.getResponse().getContentAsString();
//        mem = objectMapper.readValue(body, MemoryDto.class);
//        mem.setBrand("AfterUpdate");
//        mem.setModel("AfterUpdate");
//
//        MvcResult res = this.mockMvc.perform(put(Uris.MEMORY)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(mem)))
//                .andExpect(status().isOk()).andReturn();
//
//
//        //Then the updated MemoryDto is returned
//        String bodyres = res.getResponse().getContentAsString();
//
//        assertThat(bodyres).isEqualTo(mem.toString());
//    }
//
//    @Test
//    public void putNotExistingMemoryShouldReturnNotFound() throws Exception {
//        MemoryDto mem = new MemoryDto(1111L,"putNotExistentMemory", "AAA","putNotExistentMemory", "DDR4",500, 8);
//        this.mockMvc.perform(put(Uris.MEMORY)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(mem)))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void deleteByIdShouldReturnOk() throws Exception {
//        MemoryDto mem = new MemoryDto("deleteByIDMemory", "deleteByIDMemory", "AAA","DDR4",500, 8);
//        MvcResult result = this.mockMvc.perform(post(Uris.MEMORY)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(mem)))
//                .andExpect(status().isCreated())
//                .andReturn();
//        String body = result.getResponse().getContentAsString();
//        mem = objectMapper.readValue(body, MemoryDto.class);
//        this.mockMvc.perform(delete(Uris.MEMORY + Uris.ID, mem.getId())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void deleteByNonExistentIdShouldReturnNotFound() throws Exception{
//        this.mockMvc.perform(delete(Uris.MEMORY + Uris.ID, 12313131L)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//
//    @Test
//    public void deleteByResourceShouldReturnOk() throws Exception {
//        MemoryDto mem = new MemoryDto("deleteResourceMemory", "deleteResourceMemory", "AAA", "DDR4",500, 8);
//        MvcResult result = this.mockMvc.perform(post(Uris.MEMORY)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(mem)))
//                .andExpect(status().isCreated())
//                .andReturn();
//        String body = result.getResponse().getContentAsString();
//        mem = objectMapper.readValue(body, MemoryDto.class);
//        this.mockMvc.perform(delete(Uris.MEMORY)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(mem)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void deleteByNonExistentResourceShouldReturnNotFound() throws Exception {
//        MemoryDto mem = new MemoryDto(111111L,"deleteNonExistentResourceMemory", "AAA","deleteNonExistentResourceMemory", "DDR4",500, 8);
//        this.mockMvc.perform(delete(Uris.MEMORY)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(mem)))
//                .andExpect(status().isNotFound());
//    }
//
//    public void findMemoryByBrandAndModelShouldReturnMemoryDto() throws Exception {
//        MemoryDto mem = new MemoryDto("FindBrand", "FindModel", "AAA","DDR4",500, 8);
//
//        this.mockMvc.perform(post(Uris.MEMORY)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(mem)))
//                .andExpect(status().isCreated());
//
//
//        MvcResult result = this.mockMvc.perform((get(Uris.MEMORY + Uris.SEARCH))
//                .param("brand", mem.getBrand())
//                .param("model", mem.getModel())).andExpect(status().isOk()).andReturn();
//
//        String body = result.getResponse().getContentAsString();
//
//        MemoryDto memResult = objectMapper.readValue(body, MemoryDto.class);
//        assertThat(memResult.getBrand()).isEqualTo(mem.getBrand());
//        assertThat(memResult.getModel()).isEqualTo(mem.getModel());
//
//    }
//
//    @Test
//    public void findAllShouldReturnListOfMemory() throws Exception {
//        List<MemoryDto> memoryDtoList = new ArrayList<>();
//        MemoryDto mem1 = new MemoryDto("create1", "Saphire1", "AAA","DDR4",100, 8);
//        memoryDtoList.add(mem1);
//        MemoryDto mem2 = new MemoryDto("create2", "Saphire2", "AAA","DDR4", 200, 8);
//        memoryDtoList.add(mem2);
//        MemoryDto mem3 = new MemoryDto("create3", "Saphire3", "AAA","DDR4",300, 8);
//        memoryDtoList.add(mem3);
//        MemoryDto mem4 = new MemoryDto("create4", "Saphire4", "AAA","DDR4", 400, 8);
//        memoryDtoList.add(mem4);
//        memoryDtoList.forEach(memoryDto -> {
//        try {
//            this.mockMvc.perform(post(Uris.MEMORY)
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(objectMapper.writeValueAsString(memoryDto)))
//                    .andExpect(status().isCreated());
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    });
//        IntStream.range(0, memoryDtoList.size()).forEach(index -> memoryDtoList.get(index).setId(index + 1L));
//        this.mockMvc.perform(get(Uris.MEMORY))
//                .andDo(print())
//                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(memoryDtoList.size())));
//    }
//
//    public void findAllOnEmptyCollectionShouldReturnEmptyListOfMemory() throws Exception {
//        this.mockMvc.perform(get(Uris.MEMORY))
//                .andDo(print())
//                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
//    }
}