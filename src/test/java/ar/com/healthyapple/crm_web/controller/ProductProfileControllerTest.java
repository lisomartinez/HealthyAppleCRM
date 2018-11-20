package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.Config.SecurityConfig;
import ar.com.healthyapple.crm_web.dto.Product.ProductProfileDto;
import ar.com.healthyapple.crm_web.model.Product.ProductProfile;
import ar.com.healthyapple.crm_web.service.Product.ProductProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest({ProductProfileController.class})
@ContextConfiguration(classes={ProductProfileController.class, SecurityConfig.class})
class ProductProfileControllerTest {

    private final static Long ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @MockBean
    private ProductProfileService service;

    private ProductProfile request;

    private ProductProfile response;

    private ProductProfileDto requestDto;

    private ProductProfileDto responseDto;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createP() {
    }

    @Test
    void read() {
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