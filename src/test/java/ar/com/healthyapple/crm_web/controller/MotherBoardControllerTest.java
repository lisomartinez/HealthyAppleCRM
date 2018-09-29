package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.Config.SecurityConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.doThrow;

@RunWith(SpringRunner.class)
@WebMvcTest({MotherBoardController.class})
@ContextConfiguration(classes={MotherBoardController.class, SecurityConfig.class})
public class MotherBoardControllerTest {
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
//    private MotherBoardService motherBoardService;
//
//
//    private MotherBoard motherBoardRequest;
//
//    private MotherBoard motherBoardResponse;
//
//    private MotherBoardDto motherBoardDtoRequest;
//
//    private MotherBoardDto motherBoardDtoResponse;
//
//    @Before
//    public void setUp() throws Exception {
//        motherBoardRequest = new MotherBoard("Intel", "B85m", "AAA","775");
//        motherBoardResponse = new MotherBoard("Intel", "B85m","AAA", "775");
//        motherBoardResponse.setId(1L);
//        motherBoardDtoRequest = new MotherBoardDto("Intel", "B85m", "AAA","775");
//        motherBoardDtoResponse = new MotherBoardDto(1L,"Intel", "AAA","B85m", "775");
//
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//    @Test
//    public void createShouldReturnCreated() throws Exception {
//
//        when(entityDtoConverter.convertToEntity(motherBoardDtoRequest, MotherBoard.class)).thenReturn(motherBoardRequest);
//        when(entityDtoConverter.convertToDto(motherBoardResponse, MotherBoardDto.class)).thenReturn(motherBoardDtoResponse);
//        when(motherBoardService.create(motherBoardRequest)).thenReturn(motherBoardResponse);
//
//        MvcResult result = this.mockMvc.perform(post(Uris.MOTHERBOARDS)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(motherBoardDtoRequest)))
//                .andExpect(status().isCreated())
//                .andReturn();
//
//        String body = result.getResponse().getContentAsString();
//        MotherBoardDto motherBoardDto = objectMapper.readValue(body, MotherBoardDto.class);
//        assertThat(motherBoardDto).isEqualTo(motherBoardDtoResponse);
//
//    }
//
//    @Test
//    public void createDuplicateShouldReturnAlreadyExistsException() throws Exception {
//
//        when(entityDtoConverter.convertToEntity(motherBoardDtoRequest, MotherBoard.class)).thenReturn(motherBoardRequest);
//        doThrow(AlreadyExistException.class).when(motherBoardService).create(motherBoardRequest);
//
//        this.mockMvc.perform(post(Uris.MOTHERBOARDS)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(motherBoardDtoRequest)));
//
//    }
//
//    @Test
//    public void readShouldReturnMotherBoardDto() throws Exception {
//
//        when(entityDtoConverter.convertToDto(motherBoardResponse, MotherBoardDto.class)).thenReturn(motherBoardDtoResponse);
//        when(motherBoardService.read(1L)).thenReturn(   motherBoardResponse);
//
//        MvcResult result =  this.mockMvc.perform(get(Uris.MOTHERBOARDS + Uris.ID, 1L)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String body = result.getResponse().getContentAsString();
//        MotherBoardDto motherBoardDto = objectMapper.readValue(body, MotherBoardDto.class);
//
//        assertThat(motherBoardDto).isEqualTo(motherBoardDtoResponse);
//    }
//
//    @Test
//    public void updateShouldReturnUpdatedMotherBoardDto() throws Exception {
//
//        when(entityDtoConverter.convertToEntity(motherBoardDtoRequest, MotherBoard.class)).thenReturn(motherBoardRequest);
//        when(entityDtoConverter.convertToDto(motherBoardResponse, MotherBoardDto.class)).thenReturn(motherBoardDtoResponse);
//        when(motherBoardService.update(motherBoardRequest)).thenReturn(motherBoardResponse);
//
//        MvcResult result =  this.mockMvc.perform(put(Uris.MOTHERBOARDS)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(motherBoardDtoRequest)))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String body = result.getResponse().getContentAsString();
//        MotherBoardDto motherBoardDto = objectMapper.readValue(body, MotherBoardDto.class);
//
//        assertThat(motherBoardDto).isEqualTo(motherBoardDtoResponse);
//    }
//
//    @Test
//    public void deleteByIDShouldReturnOk() throws Exception {
//
//        doNothing().when(motherBoardService).deleteById(1L);
//
//        this.mockMvc.perform(delete(Uris.MOTHERBOARDS + Uris.ID, 1L)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void deleteByNonExistentIDShouldReturnNotFoundException() throws Exception {
//
//        doThrow(NotFoundException.class).when(motherBoardService).deleteById(1L);
//
//        this.mockMvc.perform(delete(Uris.MOTHERBOARDS + Uris.ID, 2L)
//                .contentType(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    public void deleteByResourceShouldReturnOk() throws Exception {
//
//        when(entityDtoConverter.convertToEntity(motherBoardDtoRequest, MotherBoard.class)).thenReturn(motherBoardRequest);
//        doNothing().when(motherBoardService).delete(motherBoardRequest);
//
//        this.mockMvc.perform(delete(Uris.MOTHERBOARDS)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(motherBoardDtoRequest)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void deleteByNonExistentResourceShouldReturnNotFoundException() throws  Exception{
//        when(entityDtoConverter.convertToEntity(motherBoardDtoRequest, MotherBoard.class)).thenReturn(motherBoardRequest);
//        doNothing().when(motherBoardService).delete(motherBoardRequest);
//
//        this.mockMvc.perform(delete(Uris.MOTHERBOARDS)
//                .contentType(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    public void findByBrandAndModel() throws Exception {
//        when(entityDtoConverter.convertToEntity(motherBoardDtoRequest, MotherBoard.class)).thenReturn(motherBoardRequest);
//        when(entityDtoConverter.convertToDto(motherBoardResponse, MotherBoardDto.class)).thenReturn(motherBoardDtoResponse);
//        when(motherBoardService.findByBrandAndModel(motherBoardRequest.getBrand(), motherBoardRequest.getModel())).thenReturn(motherBoardResponse);
//
//        MvcResult result = this.mockMvc.perform((get(Uris.MOTHERBOARDS + Uris.SEARCH))
//                .param("brand", motherBoardDtoRequest.getBrand())
//                .param("model", motherBoardDtoRequest.getModel())).andExpect(status().isOk()).andReturn();
//        String body = result.getResponse().getContentAsString();
//        MotherBoardDto motherBoardDto = objectMapper.readValue(body, MotherBoardDto.class);
//
//        assertThat(motherBoardDto).isEqualTo(motherBoardDtoResponse);
//    }
//
//    @Test
//    @Ignore
//    public void findAll() {
//
//    }
}