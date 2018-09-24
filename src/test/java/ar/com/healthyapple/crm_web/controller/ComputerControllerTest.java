package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.Config.SecurityConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest({ComputerController.class})
@ContextConfiguration(classes={ComputerController.class, SecurityConfig.class})
public class ComputerControllerTest {
/*
    private final String MODEL_IDENTIFIER = "AAA1234";
    private final String SERIAL_NUMBER = "123";
    private final String PREINSTALLED_MACOS = "Mavericks";
    private final Double DISPLAY_SIZE = 15.0;

    private final Long ID_HACKINTOSH = 1L;
    private final Long ID_MAC = 2L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EntityDtoConverter entityDtoConverter;

    @MockBean
    private ComputerService computerService;

    private Computer hackintoshComputerRequest;

    private Computer hackintoshComputerResponse;

    private ComputerDto hackintoshComputerDtoRequest;

    private ComputerDto hackintoshComputerDtoResponse;

    private Computer macComputerRequest;

    private Computer macComputerResponse;

    private ComputerDto macComputerDtoRequest;

    private ComputerDto macComputerDtoResponse;


    @Before
    public void setUp() throws Exception {
        macComputerRequest = new MacComputer(
                new MotherBoard(),
                new Processor(),
                new Memory(),
                new HardDrive(),
                MODEL_IDENTIFIER,
                SERIAL_NUMBER,
                LocalDate.of(2010,8,4),
                PREINSTALLED_MACOS,
                DISPLAY_SIZE
        );

        macComputerResponse = new MacComputer(
                new MotherBoard(),
                new Processor(),
                new Memory(),
                new HardDrive(),
                MODEL_IDENTIFIER,
                SERIAL_NUMBER,
                LocalDate.of(2010,8,4),
                PREINSTALLED_MACOS,
                DISPLAY_SIZE
        );

        macComputerResponse.setId(ID_MAC);

        macComputerDtoRequest = new MacComputerDto(
                new MotherBoardDto(),
                new ProcessorDto(),
                new MemoryDto(),
                new HardDriveDto(),
                MODEL_IDENTIFIER,
                SERIAL_NUMBER,
                LocalDate.of(2010,8,4),
                PREINSTALLED_MACOS,
                DISPLAY_SIZE
        );

        macComputerDtoResponse = new MacComputerDto(
                new MotherBoardDto(),
                new ProcessorDto(),
                new MemoryDto(),
                new HardDriveDto(),
                MODEL_IDENTIFIER,
                SERIAL_NUMBER,
                LocalDate.of(2010,8,4),
                PREINSTALLED_MACOS,
                DISPLAY_SIZE
        );

        macComputerDtoResponse.setId(ID_MAC);

        hackintoshComputerRequest = new HackintoshComputer(
                new MotherBoard(),
                new Processor(),
                new Memory(),
                new HardDrive(),
                new PcCase(),
                new PowerSupply(),
                new VideoCard()
        );

        hackintoshComputerResponse = new HackintoshComputer(
                new MotherBoard(),
                new Processor(),
                new Memory(),
                new HardDrive(),
                new PcCase(),
                new PowerSupply(),
                new VideoCard()
        );
        hackintoshComputerResponse.setId(ID_HACKINTOSH);

        hackintoshComputerDtoRequest = new HackintoshComputerDto(
                new MotherBoardDto(),
                new ProcessorDto(),
                new MemoryDto(),
                new HardDriveDto(),
                new PcCaseDto(),
                new PowerSupplyDto(),
                new VideoCardDto()
        );

        hackintoshComputerDtoResponse = new HackintoshComputerDto(
                new MotherBoardDto(),
                new ProcessorDto(),
                new MemoryDto(),
                new HardDriveDto(),
                new PcCaseDto(),
                new PowerSupplyDto(),
                new VideoCardDto()
        );

        hackintoshComputerDtoResponse.setId(ID_HACKINTOSH);


    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createHackintoshShouldReturnOkAndStatusCreated() throws Exception {
        ComputerDto result = createComputerDto(hackintoshComputerDtoRequest, hackintoshComputerDtoResponse, hackintoshComputerRequest, hackintoshComputerResponse);
        assertThat(result).isEqualTo(hackintoshComputerDtoResponse);
        assertThat(result.getComputerType()).isEqualTo(hackintoshComputerDtoResponse.getComputerType());

    }

    @Test
    public void createMacShouldReturnCorrectType() throws Exception{
        ComputerDto result = createComputerDto(macComputerDtoRequest, macComputerDtoResponse, macComputerRequest, macComputerResponse);
        assertThat(result).isEqualTo(macComputerDtoResponse);
        assertThat(result.getComputerType()).isEqualTo(macComputerDtoResponse.getComputerType());

    }

    private ComputerDto createComputerDto(ComputerDto computerDtoRequest, ComputerDto computerDtoResponse, Computer computerRequest, Computer computerResponse)  throws Exception{
        when(entityDtoConverter.convertToEntity(computerDtoRequest, Computer.class))
                .thenReturn(computerRequest);
        when(computerService.create(computerRequest))
                .thenReturn(computerResponse);
        when(entityDtoConverter.convertToDto(computerResponse, ComputerDto.class))
                .thenReturn(computerDtoResponse);
        String json = objectMapper.writeValueAsString(computerDtoRequest);
        MvcResult result = this.mockMvc.perform(post(Uris.COMPUTERS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        return objectMapper.readValue(body, ComputerDto.class);
    }

    @Test
    @Ignore
    public void createDuplicateShouldReturnAlreadyExistsException() {

    }

    @Test
    public void readShouldReturnDtoAndStatusOk() throws Exception {
        ComputerDto computerDto = createComputerDto(macComputerDtoRequest, macComputerDtoResponse, macComputerRequest, macComputerResponse);
        when(computerService.read(ID_MAC))
                .thenReturn(macComputerResponse);
        when(entityDtoConverter.convertToDto(macComputerResponse, ComputerDto.class))
                .thenReturn(macComputerDtoResponse);
        MvcResult result = this.mockMvc.perform(get(Uris.COMPUTERS + Uris.ID, ID_MAC)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        assertThat(objectMapper.readValue(body, ComputerDto.class)).isEqualTo(computerDto);

        computerDto = createComputerDto(hackintoshComputerDtoRequest, hackintoshComputerDtoResponse, hackintoshComputerRequest, hackintoshComputerResponse);
        when(computerService.read(ID_HACKINTOSH))
                .thenReturn(hackintoshComputerResponse);
        when(entityDtoConverter.convertToDto(hackintoshComputerResponse, ComputerDto.class))
                .thenReturn(hackintoshComputerDtoResponse);
        result = this.mockMvc.perform(get(Uris.COMPUTERS + Uris.ID, ID_HACKINTOSH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        body = result.getResponse().getContentAsString();
        assertThat(objectMapper.readValue(body, ComputerDto.class)).isEqualTo(computerDto);
    }

    @Test
    @Ignore
    public void readByNonExistentIdShouldReturnNotFound() throws Exception {

    }




    @Test
    public void update() throws Exception {
        ComputerDto computerDto = createComputerDto(macComputerDtoRequest, macComputerDtoResponse, macComputerRequest, macComputerResponse);
        when(entityDtoConverter.convertToEntity(macComputerDtoRequest, Computer.class))
                .thenReturn(macComputerRequest);
        when(computerService.update(macComputerRequest))
                .thenReturn(macComputerResponse);
        when(entityDtoConverter.convertToDto(macComputerResponse, ComputerDto.class))
                .thenReturn(macComputerDtoResponse);
        String json = objectMapper.writeValueAsString(macComputerDtoRequest);
        MvcResult result = this.mockMvc.perform(post(Uris.COMPUTERS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        assertThat(objectMapper.readValue(body, ComputerDto.class)).isEqualTo(computerDto);

        computerDto = createComputerDto(hackintoshComputerDtoRequest, hackintoshComputerDtoResponse, hackintoshComputerRequest, hackintoshComputerResponse);
        when(entityDtoConverter.convertToEntity(hackintoshComputerDtoRequest, Computer.class))
                .thenReturn(hackintoshComputerRequest);
        when(computerService.update(hackintoshComputerRequest))
                .thenReturn(hackintoshComputerResponse);
        when(entityDtoConverter.convertToDto(hackintoshComputerResponse, ComputerDto.class))
                .thenReturn(hackintoshComputerDtoResponse);
        json = objectMapper.writeValueAsString(hackintoshComputerDtoRequest);
        result = this.mockMvc.perform(post(Uris.COMPUTERS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andReturn();
        body = result.getResponse().getContentAsString();
        assertThat(objectMapper.readValue(body, ComputerDto.class)).isEqualTo(computerDto);
    }

    @Test
    @Ignore
    public void updateNonExistentIdShouldReturnNotFound() throws Exception {

    }

    @Test
    public void deleteById() throws Exception {
        doNothing().when(computerService).deleteById(ID_MAC);
        doNothing().when(computerService).deleteById(ID_HACKINTOSH);
        this.mockMvc.perform(delete(Uris.COMPUTERS + Uris.ID, ID_MAC))
                .andExpect(status().isOk());
        this.mockMvc.perform(delete(Uris.COMPUTERS + Uris.ID, ID_HACKINTOSH))
                .andExpect(status().isOk());
    }

    @Test
    @Ignore
    public void deleteByNonExistentIdShouldReturnNotFound() throws Exception {

    }

    @Test
    public void deleteByResource() throws Exception {
        ComputerDto computerDto = createComputerDto(macComputerDtoRequest, macComputerDtoResponse, macComputerRequest, macComputerResponse);
        when(entityDtoConverter.convertToEntity(macComputerDtoRequest, Computer.class))
                .thenReturn(macComputerRequest);
        doNothing().when(computerService).delete(macComputerRequest);
        String json = objectMapper.writeValueAsString(macComputerDtoRequest);
        this.mockMvc.perform(delete(Uris.COMPUTERS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
        computerDto = createComputerDto(hackintoshComputerDtoRequest, hackintoshComputerDtoResponse, hackintoshComputerRequest, hackintoshComputerResponse);
        doNothing().when(computerService).delete(hackintoshComputerRequest);
        when(entityDtoConverter.convertToEntity(hackintoshComputerDtoRequest, Computer.class))
                .thenReturn(hackintoshComputerRequest);
        json =  objectMapper.writeValueAsString(hackintoshComputerDtoRequest);
        this.mockMvc.perform(delete(Uris.COMPUTERS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @Ignore
    public void deleteNonExistentResourceShouldReturnNotFound() throws NotFoundException {

    }

    @Test
    @Ignore
    public void findByBrandAndModel() throws Exception{

    }

    @Test
    @Ignore
    public void findByNonExistentBrandAndModelShouldReturnNotFound() throws Exception {

    }

    @Test
    @Ignore
    public void findAll() {
    }*/
}