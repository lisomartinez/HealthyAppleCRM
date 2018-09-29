package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.Config.SecurityConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@RunWith(SpringRunner.class)
@WebMvcTest({PcCaseController.class})
@ContextConfiguration(classes={PcCaseController.class, SecurityConfig.class})
public class PcCaseControllerTest {
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
//    private PcCaseService pcCaseService;
//
//    private PcCase pcCaseRequest;
//
//    private PcCase pcCaseResponse;
//
//    private PcCaseDto pcCaseDtoRequest;
//
//    private PcCaseDto pcCaseDtoResponse;
//
//    @Before
//    public void setUp() throws Exception {
//        pcCaseRequest = new PcCase("Sentey" , "A20", "AAA","ATX");
//        pcCaseResponse = new PcCase("Sentey" , "A20", "AAA","ATX");
//        pcCaseResponse.setId(1L);
//        pcCaseDtoRequest = new PcCaseDto("Sentey" , "A20", "AAA","ATX");
//        pcCaseDtoResponse = new PcCaseDto(1L, "Sentey", "AAA","A20", "ATX");
//    }
//
//    @Test
//    public void createShouldReturnCreated() throws Exception {
//
//        when(entityDtoConverter.convertToEntity(pcCaseDtoRequest, PcCase.class))
//                .thenReturn(pcCaseRequest);
//
//        when(pcCaseService.create(pcCaseRequest))
//                .thenReturn(pcCaseResponse);
//
//        when(entityDtoConverter.convertToDto(pcCaseResponse, PcCaseDto.class))
//                .thenReturn(pcCaseDtoResponse);
//
//        MvcResult result = this.mockMvc.perform(post(Uris.PC_CASE)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(pcCaseDtoRequest)))
//                .andExpect(status().isCreated())
//                .andReturn();
//
//        String body = result.getResponse().getContentAsString();
//        assertThat(objectMapper.readValue(body, PcCaseDto.class)).isEqualTo(pcCaseDtoResponse);
//    }
//
//    @Test
//    public void createDuplicateShouldReturnAlreadyExistsException() throws Exception {
//
//        when(entityDtoConverter.convertToEntity(pcCaseDtoRequest, PcCase.class))
//                .thenReturn(pcCaseRequest);
//
//        when(pcCaseService.create(pcCaseRequest))
//                .thenThrow(AlreadyExistException.class);
//
//        when(entityDtoConverter.convertToDto(pcCaseResponse, PcCaseDto.class))
//                .thenReturn(pcCaseDtoResponse);
//
//             this.mockMvc.perform(post(Uris.PC_CASE)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(pcCaseDtoRequest)))
//                     .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void readShouldReturnPcCaseDtoAndStatusOk() throws Exception {
//
//        when(entityDtoConverter.convertToEntity(pcCaseDtoRequest, PcCase.class))
//                .thenReturn(pcCaseRequest);
//
//        when(pcCaseService.read(pcCaseDtoResponse.getId())).thenReturn(pcCaseResponse);
//
//        when(entityDtoConverter.convertToDto(pcCaseResponse, PcCaseDto.class))
//                .thenReturn(pcCaseDtoResponse);
//
//        MvcResult result = this.mockMvc.perform(get(Uris.PC_CASE + Uris.ID, pcCaseDtoResponse.getId())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String body = result.getResponse().getContentAsString();
//        assertThat(objectMapper.readValue(body, PcCaseDto.class)).isEqualTo(pcCaseDtoResponse);
//
//    }
//
//    @Test
//    public void readWithNonExistentIdShouldThrowNotFoundException() throws Exception {
//
//        when(entityDtoConverter.convertToEntity(pcCaseDtoRequest, PcCase.class))
//                .thenReturn(pcCaseRequest);
//
//        when(pcCaseService.read(2L)).thenThrow(NotFoundException.class);
//
//        when(entityDtoConverter.convertToDto(pcCaseResponse, PcCaseDto.class))
//                .thenReturn(pcCaseDtoResponse);
//
//        this.mockMvc.perform(get(Uris.PC_CASE + Uris.ID, 2L)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//
//    }
//
//    @Test
//    public void updateShouldReturnPcCaseDtoAndStatusOk() throws Exception {
//        when(entityDtoConverter.convertToEntity(pcCaseDtoRequest, PcCase.class))
//                .thenReturn(pcCaseRequest);
//
//        when(pcCaseService.update(pcCaseRequest)).thenReturn(pcCaseResponse);
//
//        when(entityDtoConverter.convertToDto(pcCaseResponse, PcCaseDto.class))
//                .thenReturn(pcCaseDtoResponse);
//
//        MvcResult result = this.mockMvc.perform(put(Uris.PC_CASE)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(pcCaseDtoRequest)))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String body = result.getResponse().getContentAsString();
//        assertThat(objectMapper.readValue(body, PcCaseDto.class)).isEqualTo(pcCaseDtoResponse);
//    }
//
//    @Test
//    public void updateNonExistentResourceShouldThrowNotFoundException() throws Exception {
//        when(entityDtoConverter.convertToEntity(pcCaseDtoRequest, PcCase.class))
//                .thenReturn(pcCaseRequest);
//
//        when(pcCaseService.update(pcCaseRequest)).thenThrow(NotFoundException.class);
//
//        when(entityDtoConverter.convertToDto(pcCaseResponse, PcCaseDto.class))
//                .thenReturn(pcCaseDtoResponse);
//
//        this.mockMvc.perform(put(Uris.PC_CASE)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(pcCaseDtoRequest)))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void findByBrandAndModelShouldReturnPcCaseDtoAndStatusOk() throws Exception {
//
//        when(entityDtoConverter.convertToEntity(pcCaseDtoRequest, PcCase.class))
//                .thenReturn(pcCaseRequest);
//
//        when(pcCaseService.findByBrandAndModel(pcCaseRequest.getBrand(), pcCaseDtoRequest.getModel())).thenReturn(pcCaseResponse);
//
//        when(entityDtoConverter.convertToDto(pcCaseResponse, PcCaseDto.class))
//                .thenReturn(pcCaseDtoResponse);
//
//        MvcResult result = this.mockMvc.perform(get(Uris.PC_CASE + Uris.SEARCH)
//                .param("brand", pcCaseDtoRequest.getBrand())
//                .param("model", pcCaseDtoRequest.getModel())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(pcCaseDtoRequest)))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String body = result.getResponse().getContentAsString();
//        assertThat(objectMapper.readValue(body, PcCaseDto.class)).isEqualTo(pcCaseDtoResponse);
//    }
//
//    @Test
//    public void findByNonExistentBrandAndModelShouldThrowNotFoundException() throws Exception {
//
//        when(entityDtoConverter.convertToEntity(pcCaseDtoRequest, PcCase.class))
//                .thenReturn(pcCaseRequest);
//
//        when(pcCaseService.findByBrandAndModel(pcCaseRequest.getBrand(), pcCaseDtoRequest.getModel())).thenThrow(NotFoundException.class);
//
//        when(entityDtoConverter.convertToDto(pcCaseResponse, PcCaseDto.class))
//                .thenReturn(pcCaseDtoResponse);
//
//        this.mockMvc.perform(get(Uris.PC_CASE + Uris.SEARCH)
//                .param("brand", pcCaseDtoRequest.getBrand())
//                .param("model", pcCaseDtoRequest.getModel())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(pcCaseDtoRequest)))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Ignore
//    public void findAll() throws Exception {
//
//    }
}
