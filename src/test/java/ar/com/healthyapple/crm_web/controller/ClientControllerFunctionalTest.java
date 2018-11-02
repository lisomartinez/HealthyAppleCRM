package ar.com.healthyapple.crm_web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerFunctionalTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Ignore
    public void getClientShouldReturnAllFields() {
//        MvcResult result = this.mockMvc.perform(get(Uris.CLIENTS + Uris.ID, "1111123123213")
//                   .contentType(MediaType.APPLICATION_JSON))
//                   .andExpect(status().isOk()).andReturn();
//        assertThat(result).isNotNull();
//
//
//        ClientDto cl = objectMapper.readValue(result.getResponse().getContentAsString(), ClientDto.class);
//        assertThat(cl.getProducts().get(0).getComponents()).isNotNull();
    }
}
