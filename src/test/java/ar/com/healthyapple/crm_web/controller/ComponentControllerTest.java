package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.Config.SecurityConfig;
import ar.com.healthyapple.crm_web.Utils.*;
import ar.com.healthyapple.crm_web.controller.DtoConverter.ComponentDtoConverter;
import ar.com.healthyapple.crm_web.dto.Product.ComponentDto;
import ar.com.healthyapple.crm_web.dto.Product.ComponentTypeDto;
import ar.com.healthyapple.crm_web.dto.Product.SpecificationDto;
import ar.com.healthyapple.crm_web.model.Product.Component;
import ar.com.healthyapple.crm_web.model.Product.ComponentType;
import ar.com.healthyapple.crm_web.model.Product.Specification;
import ar.com.healthyapple.crm_web.service.Product.ComponentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest({ComponentController.class})
@ContextConfiguration(classes={ComponentController.class, SecurityConfig.class})
class ComponentControllerTest {

    private final static Long ID = 1L;


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ComponentDtoConverter componentDtoConverter;

    @MockBean
    private ComponentService componentService;

    private Component request;

    private Component response;

    private ComponentDto requestDto;

    private ComponentDto responseDto;



    @BeforeEach
    void setUp() {

        ComponentType componentType = ComponentTypeFactory.makeComponentType();
        ComponentTypeDto componentTypeDto = ComponentTypeDtoFactory.makeComponentTypeDto();
        List<Specification> specifications = new ArrayList<>(Arrays.asList(SpecificationFactory.makeSpecification()));
        List<SpecificationDto> specificationsDto = new ArrayList<>(Arrays.asList(SpecificationDtoFactory.makeSpecificationDto()));
        request = ComponentFactory.makeComponent();
        response = ComponentFactory.makeComponent();
        requestDto = ComponentDtoFactory.makeComponentDto();
        responseDto = ComponentDtoFactory.makeComponentDto();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Create Component should return status created and resource")
    void createComponent() throws Exception {
        when(componentService.create(request)).thenReturn(response);
        when(componentDtoConverter.convertToEntity(requestDto)).thenReturn(request);
        when(componentDtoConverter.convertToDto(response)).thenReturn(responseDto);

        MvcResult result = this.mockMvc.perform(post(Uris.COMPONENTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        assertThat(objectMapper.readValue(body, ComponentDto.class)).isEqualTo(responseDto);
    }

    @Test
    @DisplayName("Read Component should return status Ok and Resource")
    void readComponent() throws Exception {
        when(componentService.read(ID)).thenReturn(response);
        when(componentDtoConverter.convertToDto(response)).thenReturn(responseDto);

        MvcResult result = this.mockMvc.perform(get(Uris.COMPONENTS + Uris.ID, ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        assertThat(objectMapper.readValue(body, ComponentDto.class)).isEqualTo(responseDto);
    }

    @Test
    @DisplayName("Update Component should return status Ok and Resource")
    void updateComponent() throws Exception {
        when(componentService.update(request)).thenReturn(response);
        when(componentDtoConverter.convertToEntity(requestDto)).thenReturn(request);
        when(componentDtoConverter.convertToDto(response)).thenReturn(responseDto);

        MvcResult result = this.mockMvc.perform(put(Uris.COMPONENTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        assertThat(objectMapper.readValue(body, ComponentDto.class)).isEqualTo(responseDto);
    }

    @Test
    @DisplayName("Delete Component By Id should return status Ok")
    void deleteComponentById() throws Exception {
        doNothing().when(componentService).deleteById(ID);
        this.mockMvc.perform(delete(Uris.COMPONENTS + Uris.ID, ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("Delete Component By Resource should return status Ok")
    void deleteComponent() throws Exception {
        when(componentDtoConverter.convertToEntity(requestDto)).thenReturn(request);
        doNothing().when(componentService).delete(request);
        this.mockMvc.perform(delete(Uris.COMPONENTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Disabled( "Not implemented yet" )
    void findAllComponents() {
    }
}