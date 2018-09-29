package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.Config.SecurityConfig;
import ar.com.healthyapple.crm_web.model.Computer.PowerSupply;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest({PowerSupply.class})
@ContextConfiguration(classes={PowerSupply.class, SecurityConfig.class})
public class PowerSupplyControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private EntityDtoConverter entityDtoConverter;
//
//    @MockBean
//    private PowerSupplyService powerSupplyService;
//
//    private PowerSupply powerSupplyRequest;
//
//    private PowerSupply powerSupplyResponse;
//
//    private PowerSupplyDto powerSupplyDtoRequest;
//
//    private PowerSupplyDto powerSupplyDtoResponse;
//
//    @Before
//    public void setUp() throws Exception {
//
//        powerSupplyDtoRequest = new PowerSupplyDto("Seasonic", "GT11","AAA", 500);
//
//        powerSupplyDtoResponse = new PowerSupplyDto(1L, "Seasonic", "AAA","GT11", 500);
//
//        powerSupplyRequest = new PowerSupply("Seasonic", "GT11", "AAA",500);
//
//        powerSupplyResponse = new PowerSupply("Seasonic", "GT11", "AAA",500);
//
//        powerSupplyResponse.setId(1L);
//
//    }
//
//    @Test
//    public void createShouldReturnDtoAndStatusCreated() throws Exception {
//
//        when(entityDtoConverter.convertToEntity(powerSupplyDtoRequest, PowerSupply.class))
//                .thenReturn(powerSupplyRequest);
//
//        when(powerSupplyService.create(powerSupplyRequest)).thenReturn(powerSupplyResponse);
//
//        when(entityDtoConverter.convertToDto(powerSupplyResponse, PowerSupplyDto.class)).thenReturn(powerSupplyDtoResponse);
//
//        MvcResult result = this.mockMvc.perform(post(Uris.POWER_SUPPLIES)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(powerSupplyDtoRequest)))
//                .andExpect(status().isCreated())
//                .andReturn();
//
//        String body = result.getResponse().getContentAsString();
//
//        assertThat(objectMapper.readValue(body, PowerSupplyDto.class)).isEqualTo(powerSupplyDtoRequest);
//
//    }
}
