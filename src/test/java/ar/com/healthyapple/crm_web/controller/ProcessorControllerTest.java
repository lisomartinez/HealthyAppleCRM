package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.Config.SecurityConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;

@RunWith(SpringRunner.class)
@WebMvcTest({ProcessorController.class})
@ContextConfiguration(classes={ProcessorController.class, SecurityConfig.class})
public class ProcessorControllerTest {
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
//    private ProcessorService processorService;
//
//    private Processor processorRequest;
//
//    private Processor processorResponse;
//
//    private ProcessorDto processorDtoRequest;
//
//    private ProcessorDto processorDtoResponse;
//
//
//    @Before
//    public void setUp() throws Exception {
//        processorDtoRequest = new ProcessorDto( "Intel", "i3","AAA", 4, "1150", 4000);
//        processorDtoResponse = new ProcessorDto(1L,  "Intel", "i3","AAA", 4, "1150", 4000);
//        processorRequest = new Processor("Intel", "i3", "AAA",4, "1150", 4000);
//        processorResponse = new Processor("Intel", "i3", "AAA",4, "1150", 4000);
//        processorResponse.setId(1L);
//    }
//
//    @Test
//    public void createShouldReturnDtoAndStatusCreated() throws Exception {
//        when(entityDtoConverter.convertToEntity(processorDtoRequest, Processor.class)).thenReturn(processorRequest);
//        when(processorService.create(processorRequest)).thenReturn(processorResponse);
//        when(entityDtoConverter.convertToDto(processorResponse, ProcessorDto.class)).thenReturn(processorDtoResponse);
//        MvcResult result = this.mockMvc.perform(post(Uris.PROCESSORS)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(processorDtoRequest)))
//                .andExpect(status().isCreated())
//                .andReturn();
//        String body = result.getResponse().getContentAsString();
//        assertThat(objectMapper.readValue(body, ProcessorDto.class)).isEqualTo(processorDtoResponse);
//    }
//
//    @Test
//    public void createDuplicateShouldReturnAlreadyExists() throws Exception {
//        when(entityDtoConverter.convertToEntity(processorDtoRequest, Processor.class)).thenReturn(processorRequest);
//        when(processorService.create(processorRequest)).thenThrow(AlreadyExistException.class);
//        this.mockMvc.perform(post(Uris.PROCESSORS)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(processorDtoRequest)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void readShouldReturnDtoAndStatusOk() throws Exception {
//        when(processorService.read(processorDtoResponse.getId())).thenReturn(processorResponse);
//        when(entityDtoConverter.convertToDto(processorResponse, ProcessorDto.class)).thenReturn(processorDtoResponse);
//        MvcResult result = this.mockMvc.perform(get(Uris.PROCESSORS + Uris.ID, processorResponse.getId())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//        String body = result.getResponse().getContentAsString();
//        assertThat(objectMapper.readValue(body, ProcessorDto.class)).isEqualTo(processorDtoResponse);
//
//    }
//
//    @Test
//    public void readNonExistentShouldReturnNotFound() throws Exception {
//        when(processorService.read(processorDtoResponse.getId())).thenThrow(NotFoundException.class);
//        when(entityDtoConverter.convertToDto(processorResponse, ProcessorDto.class)).thenReturn(processorDtoResponse);
//        this.mockMvc.perform(get(Uris.PROCESSORS + Uris.ID, processorResponse.getId())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void updateShouldReturnDtoAndStatusOk() throws Exception {
//        when(entityDtoConverter.convertToEntity(processorDtoRequest, Processor.class)).thenReturn(processorRequest);
//        when(processorService.update(processorRequest)).thenReturn(processorResponse);
//        when(entityDtoConverter.convertToDto(processorResponse, ProcessorDto.class)).thenReturn(processorDtoResponse);
//        MvcResult result = this.mockMvc.perform(put(Uris.PROCESSORS)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(processorDtoRequest)))
//                .andExpect(status().isOk())
//                .andReturn();
//        String body = result.getResponse().getContentAsString();
//        assertThat(objectMapper.readValue(body, ProcessorDto.class)).isEqualTo(processorDtoResponse);
//    }
//
//    @Test
//    public void updateNonExistentResourceShouldReturnNotFound() throws Exception {
//        when(entityDtoConverter.convertToEntity(processorDtoRequest, Processor.class)).thenReturn(processorRequest);
//        when(processorService.update(processorRequest)).thenThrow(NotFoundException.class);
//        this.mockMvc.perform(put(Uris.PROCESSORS)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(processorDtoRequest)))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void deleteByIdShouldReturnOk() throws Exception {
//        doNothing().when(processorService).deleteById(processorDtoResponse.getId());
//        this.mockMvc.perform(delete(Uris.PROCESSORS + Uris.ID, processorResponse.getId())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void deleteNonExistentIdShouldReturnNotFound() throws Exception {
//        doThrow(NotFoundException.class).when(processorService).deleteById(processorDtoResponse.getId());
//        this.mockMvc.perform(delete(Uris.PROCESSORS + Uris.ID, processorDtoResponse.getId())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void deleteByResourceShouldReturnOk() throws Exception {
//        when(entityDtoConverter.convertToEntity(processorDtoRequest, Processor.class))
//                .thenReturn(processorRequest);
//        doNothing().when(processorService).delete(processorRequest);
//        this.mockMvc.perform(delete(Uris.PROCESSORS)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(processorDtoRequest)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void deleteByNonExistentResourceShouldReturnNotFound() throws Exception {
//        when(entityDtoConverter.convertToEntity(processorDtoRequest, Processor.class))
//                .thenReturn(processorRequest);
//        doThrow(NotFoundException.class).when(processorService).delete(processorRequest);
//        this.mockMvc.perform(delete(Uris.PROCESSORS)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(processorDtoRequest)))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void findByBrandAndModelShouldReturnDtoAndStatusOk() throws Exception {
//        when(processorService.findByBrandAndModel(processorRequest.getBrand(), processorRequest.getModel())).thenReturn(processorResponse);
//        when(entityDtoConverter.convertToDto(processorResponse, ProcessorDto.class)).thenReturn(processorDtoResponse);
//        MvcResult result = this.mockMvc.perform(get(Uris.PROCESSORS + Uris.SEARCH)
//                .param("brand", processorDtoRequest.getBrand())
//                .param("model", processorDtoRequest.getModel())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//        String body = result.getResponse().getContentAsString();
//        assertThat(objectMapper.readValue(body, ProcessorDto.class)).isEqualTo(processorDtoResponse);
//    }
//
//    @Test
//    public void findByNonExistentBrandAndModelShouldReturn() throws Exception {
//        when(processorService.findByBrandAndModel(processorRequest.getBrand(), processorRequest.getModel())).thenThrow(NotFoundException.class);
//        this.mockMvc.perform(get(Uris.PROCESSORS + Uris.SEARCH)
//                .param("brand", processorDtoRequest.getBrand())
//                .param("model", processorDtoRequest.getModel())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Ignore
//    public void findAll() throws Exception {
//
//    }
}
