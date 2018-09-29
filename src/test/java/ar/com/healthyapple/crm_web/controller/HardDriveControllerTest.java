package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.Config.SecurityConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;

@RunWith(SpringRunner.class)
@WebMvcTest({HardDriveController.class})
@ContextConfiguration(classes={HardDriveController.class, SecurityConfig.class})
public class HardDriveControllerTest {
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
//    private HardDiskService hardDiskService;
//
//    private HardDrive hardDriveRequest;
//
//    private HardDrive hardDriveResponse;
//
//    private HardDriveDto hardDriveDtoRequest;
//
//    private HardDriveDto hardDriveDtoResponse;
//
//    @Before
//    public void setUp() throws Exception {
//        hardDriveDtoRequest = new HardDriveDto("Samsung", "860 EVO", "AAA","ssd", "500");
//        hardDriveDtoResponse = new HardDriveDto(1L,"Samsung", "AAA","860 EVO", "ssd", "500");
//        hardDriveRequest = new HardDrive("Samsung", "860 EVO", "AAA","ssd", "500");
//        hardDriveResponse = new HardDrive("Samsung", "860 EVO", "AAA","ssd", "500");
//        hardDriveResponse.setId(1L);
//    }
//
//    @Test
//    public void createShouldReturnHardDriveDtoAndStatusCreated() throws Exception {
//        when(entityDtoConverter.convertToEntity(hardDriveDtoRequest, HardDrive.class))
//                .thenReturn(hardDriveRequest);
//        when(hardDiskService.create(hardDriveRequest)).thenReturn(hardDriveResponse);
//        when(entityDtoConverter.convertToDto(hardDriveResponse, HardDriveDto.class))
//                .thenReturn(hardDriveDtoResponse);
//        MvcResult result = this.mockMvc.perform(post(Uris.HARD_DRIVES)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(hardDriveDtoRequest)))
//                .andExpect(status().isCreated())
//                .andReturn();
//        String body = result.getResponse().getContentAsString();
//        assertThat(objectMapper.readValue(body, HardDriveDto.class)).isEqualTo(hardDriveDtoResponse);
//    }
//
//    @Test
//    public void createDuplicateShouldReturnAlreadyExists() throws Exception {
//        when(entityDtoConverter.convertToEntity(hardDriveDtoRequest, HardDrive.class))
//                .thenReturn(hardDriveRequest);
//        when(hardDiskService.create(hardDriveRequest)).thenThrow(AlreadyExistException.class);
//        this.mockMvc.perform(post(Uris.HARD_DRIVES)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(hardDriveDtoRequest)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void readShouldReturnHardDriveDtoAndStatusOk() throws Exception {
//        when(entityDtoConverter.convertToEntity(hardDriveDtoRequest, HardDrive.class))
//                .thenReturn(hardDriveRequest);
//        when(hardDiskService.readById(hardDriveResponse.getId())).thenReturn(hardDriveResponse);
//        when(entityDtoConverter.convertToDto(hardDriveResponse, HardDriveDto.class))
//               .thenReturn(hardDriveDtoResponse);
//        MvcResult result = this.mockMvc.perform(get(Uris.HARD_DRIVES + Uris.ID, hardDriveResponse.getId())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//        String body = result.getResponse().getContentAsString();
//        assertThat(objectMapper.readValue(body, HardDriveDto.class)).isEqualTo(hardDriveDtoResponse);
//    }
//
//    @Test
//    public void readNonExistentResourceShouldReturnNotFound() throws Exception {
//        when(entityDtoConverter.convertToEntity(hardDriveDtoRequest, HardDrive.class))
//                .thenReturn(hardDriveRequest);
//        when(hardDiskService.readById(hardDriveResponse.getId())).thenThrow(NotFoundException.class);
//        this.mockMvc.perform(get(Uris.HARD_DRIVES + Uris.ID, hardDriveResponse.getId())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void updateShouldReturnHardDriveDtoAndStautsOk() throws Exception {
//        when(entityDtoConverter.convertToEntity(hardDriveDtoRequest, HardDrive.class))
//                .thenReturn(hardDriveRequest);
//        when(hardDiskService.update(hardDriveRequest)).thenReturn(hardDriveResponse);
//        when(entityDtoConverter.convertToDto(hardDriveResponse, HardDriveDto.class))
//                .thenReturn(hardDriveDtoResponse);
//        MvcResult result = this.mockMvc.perform(put(Uris.HARD_DRIVES)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(hardDriveDtoRequest)))
//                .andExpect(status().isOk())
//                .andReturn();
//        String body = result.getResponse().getContentAsString();
//        assertThat(objectMapper.readValue(body, HardDriveDto.class)).isEqualTo(hardDriveDtoResponse);
//    }
//
//    @Test
//    public void updateNonExistentResourceShouldReturnNotFound() throws Exception {
//        when(entityDtoConverter.convertToEntity(hardDriveDtoRequest, HardDrive.class))
//                .thenReturn(hardDriveRequest);
//        when(hardDiskService.update(hardDriveRequest)).thenThrow(NotFoundException.class);
//        this.mockMvc.perform(put(Uris.HARD_DRIVES)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(hardDriveDtoRequest)))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void deleteByIdShouldReturnOk() throws Exception {
//        doNothing().when(hardDiskService).deleteById(hardDriveResponse.getId());
//        this.mockMvc.perform(delete(Uris.HARD_DRIVES + Uris.ID, hardDriveResponse.getId()))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void deleteByNonExistentIdShouldReturnNotFound() throws Exception {
//        doThrow(NotFoundException.class).when(hardDiskService).deleteById(hardDriveResponse.getId());
//        this.mockMvc.perform(delete(Uris.HARD_DRIVES + Uris.ID, hardDriveResponse.getId()))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void deleteByResourceShouldReturnOk() throws Exception {
//        when(entityDtoConverter.convertToEntity(hardDriveDtoRequest, HardDrive.class))
//                .thenReturn(hardDriveRequest);
//        doNothing().when(hardDiskService).delete(hardDriveRequest);
//        this.mockMvc.perform(delete(Uris.HARD_DRIVES )
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(hardDriveDtoRequest)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void findByBrandAndModelShouldReturnDtoAndOk() throws Exception {
//        when(hardDiskService.findHardDriveByBrandAndModel(hardDriveRequest.getBrand(), hardDriveRequest.getModel()))
//                .thenReturn(hardDriveResponse);
//        when(entityDtoConverter.convertToDto(hardDriveResponse, HardDriveDto.class))
//                .thenReturn(hardDriveDtoResponse);
//        MvcResult result = this.mockMvc.perform(get(Uris.HARD_DRIVES + Uris.SEARCH)
//                .param("brand", hardDriveDtoRequest.getBrand())
//                .param("model", hardDriveDtoRequest.getModel())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andReturn();
//        String body = result.getResponse().getContentAsString();
//        assertThat(objectMapper.readValue(body, HardDriveDto.class)).isEqualTo(hardDriveDtoResponse);
//    }
//
//    @Test
//    public void findByNonExistentBrandAndModelShouldReturnNotFound() throws Exception {
//        when(hardDiskService.findHardDriveByBrandAndModel(hardDriveRequest.getBrand(), hardDriveRequest.getModel()))
//                .thenThrow(NotFoundException.class);
//        this.mockMvc.perform(get(Uris.HARD_DRIVES + Uris.SEARCH)
//                .param("brand", hardDriveDtoRequest.getBrand())
//                .param("model", hardDriveDtoRequest.getModel())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Ignore
//    public void findAll() throws Exception {
//
//
//    }
}

