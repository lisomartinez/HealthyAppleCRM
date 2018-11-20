package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.Config.SecurityConfig;
import ar.com.healthyapple.crm_web.Utils.ComponentProfileDtoFactory;
import ar.com.healthyapple.crm_web.Utils.ComponentProfileFactory;
import ar.com.healthyapple.crm_web.controller.DtoConverter.ComponentProfileDtoConverter;
import ar.com.healthyapple.crm_web.dto.Product.ComponentProfileDto;
import ar.com.healthyapple.crm_web.model.Product.ComponentProfile;
import ar.com.healthyapple.crm_web.service.Product.ComponentProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest({ComponentProfileController.class})
@ContextConfiguration(classes={ComponentProfileController.class, SecurityConfig.class})
class ComponentProfileControllerTest {

    private final static Long ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ComponentProfileService componentProfileService;

    @MockBean
    private ComponentProfileDtoConverter componentProfileDtoConverter;

    private ComponentProfile request;

    private ComponentProfile response;

    private ComponentProfileDto requestDto;

    private ComponentProfileDto responseDto;

    @BeforeEach
    void setUp() {
       request = ComponentProfileFactory.makeComponentProfile();
        response = ComponentProfileFactory.makeComponentProfile();
        requestDto = ComponentProfileDtoFactory.makeComponentProfileDto();
        responseDto = ComponentProfileDtoFactory.makeComponentProfileDto();
        responseDto.setId(ID);
        response.setId(ID);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Create Component Profile")
    void create() throws Exception {
        when(componentProfileDtoConverter.convertToEntity(requestDto)).thenReturn(request);
        when(componentProfileService.create(request)).thenReturn(response);
        when(componentProfileDtoConverter.convertToDto(response)).thenReturn(responseDto);
        MvcResult result = this.mockMvc.perform(post(Uris.COMPONENTS + Uris.PROFILES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        assertThat(body).isNotEmpty();
        assertThat(objectMapper.readValue(body, ComponentProfileDto.class)).isEqualTo(responseDto);
    }

    @Test
    void read() throws Exception {
        when(componentProfileService.read(ID)).thenReturn(response);
        when(componentProfileDtoConverter.convertToDto(response)).thenReturn(responseDto);
        MvcResult result = this.mockMvc.perform(get(Uris.COMPONENTS + Uris.PROFILES, ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        assertThat(body).isNotEmpty();
//        assertThat(objectMapper.readValue(body, ComponentProfileDto.class)).isEqualTo(responseDto);
    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
    }

    @Test
    void getAllNames() {
    }
}