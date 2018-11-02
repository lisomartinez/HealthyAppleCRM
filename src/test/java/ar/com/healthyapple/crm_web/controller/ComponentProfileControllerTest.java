package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.Config.SecurityConfig;
import ar.com.healthyapple.crm_web.dto.Product.ComponentProfileDto;
import ar.com.healthyapple.crm_web.model.Product.ComponentProfile;
import ar.com.healthyapple.crm_web.repository.Product.ComponentProfileRepository;
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
    private EntityDtoConverter entityDtoConverter;

    private ComponentProfile request;

    private ComponentProfile response;

    private ComponentProfileDto requestDto;

    private ComponentProfileDto responseDto;

    private Map<String, String> specifications;

    @BeforeEach
    void setUp() {
        specifications = new HashMap<>();
//        specifications.put("name_1", "description_1");
//        specifications.put("name_2", "description_2");
//        specifications.put("name_3", "description_3");

        request = new ComponentProfile("type", "description", Boolean.FALSE, new HashMap<>());
        response = new ComponentProfile("type", "description", Boolean.FALSE, new HashMap<>());
        requestDto = new ComponentProfileDto("type", "description", Boolean.FALSE, new HashMap<>());
        responseDto = new ComponentProfileDto("type", "description", Boolean.FALSE, new HashMap<>());
        responseDto.setId(ID);
        response.setId(ID);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Create Component Profile")
    void create() throws Exception {
        when(entityDtoConverter.convertToEntity(requestDto, ComponentProfile.class)).thenReturn(request);
        when(componentProfileService.create(request)).thenReturn(response);
        when(entityDtoConverter.convertToDto(response, ComponentProfileDto.class)).thenReturn(responseDto);
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
        when(componentProfileService.read(response.getId())).thenReturn(response);
        when(entityDtoConverter.convertToDto(response, ComponentProfileDto.class)).thenReturn(responseDto);
        MvcResult result = this.mockMvc.perform(get(Uris.COMPONENTS + Uris.PROFILES, response.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        assertThat(body).isNotEmpty();
        assertThat(objectMapper.readValue(body, ComponentProfileDto.class)).isEqualTo(responseDto);
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