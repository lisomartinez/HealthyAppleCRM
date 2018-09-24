package ar.com.healthyapple.crm_web.service.Computer;

import ar.com.healthyapple.crm_web.exceptions.AlreadyExistException;
import ar.com.healthyapple.crm_web.exceptions.NotFoundException;
import ar.com.healthyapple.crm_web.model.Computer.*;
import ar.com.healthyapple.crm_web.model.TechnicalSpecification;
import ar.com.healthyapple.crm_web.repository.TechnicalSpecificationRespository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class TechnicalSpecificationServiceImplTest {

    private final String MAC_NAME = "imac";
    private final String MAC_DESCRIPTION = "mac_description";
    private final String MODEL_IDENTIFIER = "AAA1234";
    private final String SERIAL_NUMBER = "123";
    private final String PREINSTALLED_MACOS = "Mavericks";
    private final Double DISPLAY_SIZE = 15.0;

    private final Long ID_HACKINTOSH = 1L;
    private final Long ID_MAC = 2L;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @TestConfiguration
    static class TechnicalSpecificationServiceTestContextConfiguration {

        @Bean
        public TechnicalSpecificationService TechnicalSpecificationService(TechnicalSpecificationRespository technicalSpecificationRepository) {
            return new TechnicalSpecificationServiceImpl(technicalSpecificationRepository);
        }
    }

    @Autowired
    private TechnicalSpecificationService technicalSpecificationService;

    @MockBean
    private  TechnicalSpecificationRespository technicalSpecificationRepository;

    private TechnicalSpecification hackintoshComputerRequest;

    private TechnicalSpecification hackintoshComputerResponse;

    private TechnicalSpecification macComputerRequest;

    private TechnicalSpecification macComputerResponse;

    @Before
    public void setUp() throws Exception {


        macComputerRequest = new MacComputer(
                MAC_NAME,
                MAC_DESCRIPTION,
                MODEL_IDENTIFIER,
                SERIAL_NUMBER,
                PREINSTALLED_MACOS,
                LocalDate.of(2010,8,4),
                DISPLAY_SIZE,
                Arrays.asList(new Memory()),
                Arrays.asList(new HardDrive()),
                new PowerSupply(),
                new Battery()
        );

        macComputerResponse = new MacComputer(
                MAC_NAME,
                MAC_DESCRIPTION,
                MODEL_IDENTIFIER,
                SERIAL_NUMBER,
                PREINSTALLED_MACOS,
                LocalDate.of(2010,8,4),
                DISPLAY_SIZE,
                Arrays.asList(new Memory()),
                Arrays.asList(new HardDrive()),
                new PowerSupply(),
                new Battery()
        );

        macComputerResponse.setId(ID_MAC);


        hackintoshComputerRequest = new HackintoshComputer(
                new MotherBoard(),
                new Processor(),
                Arrays.asList(new Memory()),
                Arrays.asList(new HardDrive()),
                new PcCase(),
                new PowerSupply(),
                new VideoCard()
        );

        hackintoshComputerResponse = new HackintoshComputer(
                new MotherBoard(),
                new Processor(),
                Arrays.asList(new Memory()),
                Arrays.asList(new HardDrive()),
                new PcCase(),
                new PowerSupply(),
                new VideoCard()
        );
        hackintoshComputerResponse.setId(ID_HACKINTOSH);

    }

    @Test
    public void createMac() throws AlreadyExistException {
        when(technicalSpecificationRepository.findById(ID_MAC))
                .thenReturn(Optional.empty());
        when(technicalSpecificationRepository.save(macComputerRequest))
                .thenReturn(macComputerResponse);
        TechnicalSpecification result = technicalSpecificationService.create(macComputerRequest);
        assertThat(result).isEqualTo(macComputerResponse);
    }

    @Test
    public void createHackintosh() throws AlreadyExistException {
        when(technicalSpecificationRepository.findById(ID_HACKINTOSH))
                .thenReturn(Optional.empty());
        when(technicalSpecificationRepository.save(hackintoshComputerRequest))
                .thenReturn(hackintoshComputerResponse);
        TechnicalSpecification result = technicalSpecificationService.create(hackintoshComputerRequest);
        assertThat(result).isEqualTo(hackintoshComputerResponse);
    }



    @Test
    public void readMac() {
        macComputerRequest.setId(1L);
        when(technicalSpecificationRepository.findById(macComputerRequest.getId()))
                .thenReturn(Optional.of(macComputerResponse));
        TechnicalSpecification result = technicalSpecificationService.read(macComputerRequest.getId());
        assertThat(result).isEqualTo(macComputerResponse);
    }

    @Test
    public void readHackintosh() {
        hackintoshComputerRequest.setId(1L);
        when(technicalSpecificationRepository.findById(hackintoshComputerRequest.getId()))
                .thenReturn(Optional.of(hackintoshComputerResponse));
        TechnicalSpecification result = technicalSpecificationService.read(hackintoshComputerRequest.getId());
        assertThat(result).isEqualTo(hackintoshComputerResponse);
    }

    @Test
    public void readMacByNonExistentIdShouldThrowNotFoundException() {
        thrown.expect(NotFoundException.class);
        macComputerRequest.setId(1L);
        when(technicalSpecificationRepository.findById(macComputerRequest.getId()))
                .thenReturn(Optional.empty());
        technicalSpecificationService.read(macComputerRequest.getId());
    }

    @Test
    public void readHackintoshByNonExistentIdShouldThrowNotFoundException() {
        thrown.expect(NotFoundException.class);
        hackintoshComputerRequest.setId(1L);
        when(technicalSpecificationRepository.findById(hackintoshComputerRequest.getId()))
                .thenReturn(Optional.empty());
        technicalSpecificationService.read(hackintoshComputerRequest.getId());
    }

    @Test
    public void updateMac() {
        when(technicalSpecificationRepository.findById(macComputerRequest.getId()))
                .thenReturn(Optional.of(macComputerRequest));
        when(technicalSpecificationRepository.save(macComputerRequest)).thenReturn(macComputerResponse);
        TechnicalSpecification result = technicalSpecificationService.update(macComputerRequest);
        assertThat(result).isEqualTo(macComputerResponse);
    }

    @Test
    public void updateMacByNonExistentResourceShouldThrowNotFoundException() {
        thrown.expect(NotFoundException.class);
        when(technicalSpecificationRepository.findById(macComputerRequest.getId()))
                .thenReturn(Optional.empty());
        when(technicalSpecificationRepository.save(macComputerRequest)).thenReturn(macComputerResponse);
        technicalSpecificationService.update(macComputerRequest);
    }

    @Test
    public void updateHackintosh() {
        when(technicalSpecificationRepository.findById(hackintoshComputerRequest.getId())).thenReturn(Optional.of(hackintoshComputerRequest));
        when(technicalSpecificationRepository.save(hackintoshComputerRequest))
                .thenReturn(hackintoshComputerResponse);
        TechnicalSpecification result = technicalSpecificationService.update(hackintoshComputerRequest);
        assertThat(result).isEqualTo(hackintoshComputerResponse);
    }

    @Test
    public void updateHackintoshByNonExistentResourceShouldThrowNotFoundException() {
        thrown.expect(NotFoundException.class);
        when(technicalSpecificationRepository.findById(hackintoshComputerRequest.getId()))
                .thenReturn(Optional.empty());
        when(technicalSpecificationRepository.save(hackintoshComputerRequest))
                .thenReturn(hackintoshComputerResponse);
        technicalSpecificationService.update(hackintoshComputerRequest);
    }

    @Test
    public void deleteMacById() {
        when(technicalSpecificationRepository.findById(macComputerRequest.getId()))
                .thenReturn(Optional.of(macComputerRequest));
        doNothing().when(technicalSpecificationRepository).deleteById(macComputerRequest.getId());
        technicalSpecificationService.deleteById(macComputerRequest.getId());
    }

    @Test
    public void deleteMacByNonExistentIdShouldThrowNotFoundException() {
        thrown.expect(NotFoundException.class);
        when(technicalSpecificationRepository.findById(macComputerRequest.getId()))
                .thenReturn(Optional.empty());
        technicalSpecificationService.deleteById(macComputerRequest.getId());
    }

    @Test
    public void deleteHackintoshById() {
        when(technicalSpecificationRepository.findById(hackintoshComputerRequest.getId()))
                .thenReturn(Optional.of(hackintoshComputerRequest));
        doNothing().when(technicalSpecificationRepository).deleteById(hackintoshComputerRequest.getId());
        technicalSpecificationService.deleteById(hackintoshComputerRequest.getId());
    }

    @Test
    public void deleteHackintoshByNonExistentIdShouldThrowNotFoundException() {
        thrown.expect(NotFoundException.class);
        when(technicalSpecificationRepository.findById(hackintoshComputerRequest.getId()))
                .thenReturn(Optional.empty());
        technicalSpecificationService.deleteById(hackintoshComputerRequest.getId());
    }

    @Test
    public void deleteMac() {
        when(technicalSpecificationRepository.findById(macComputerRequest.getId()))
                .thenReturn(Optional.of(macComputerRequest));
        doNothing().when(technicalSpecificationRepository).delete(macComputerRequest);
        technicalSpecificationService.delete(macComputerRequest);
    }

    @Test
    public void deleteMacByNonExistentResourceShouldThrowNotFoundException() {
        thrown.expect(NotFoundException.class);
        when(technicalSpecificationRepository.findById(macComputerRequest.getId()))
                .thenReturn(Optional.empty());
        doNothing().when(technicalSpecificationRepository).delete(macComputerRequest);
        technicalSpecificationService.delete(macComputerRequest);
    }

    @Test
    public void deleteHackintosh() {
        when(technicalSpecificationRepository.findById(hackintoshComputerRequest.getId()))
                .thenReturn(Optional.of(hackintoshComputerRequest));
        doNothing().when(technicalSpecificationRepository).delete(hackintoshComputerRequest);
        technicalSpecificationService.delete(hackintoshComputerRequest);
    }

    @Test
    public void deleteHackintoshByNonExistentResourceShouldThrowNotFoundException() {
        thrown.expect(NotFoundException.class);
        when(technicalSpecificationRepository.findById(hackintoshComputerRequest.getId()))
                .thenReturn(Optional.empty());
        doNothing().when(technicalSpecificationRepository).delete(hackintoshComputerRequest);
        technicalSpecificationService.delete(hackintoshComputerRequest);
    }

    @Test
    @Ignore
    public void findAll() {
//        List<TechnicalSpecification> computerList = new ArrayList<>();
//        computerList.add( new MacComputer(
//                new MotherBoard(),
//                new Processor(),
//                new Memory(),
//                new HardDrive(),
//                MODEL_IDENTIFIER,
//                SERIAL_NUMBER,
//                LocalDate.of(2010,8,4),
//                PREINSTALLED_MACOS,
//                DISPLAY_SIZE
//        ));
//        computerList.add( new MacComputer(
//                new MotherBoard(),
//                new Processor(),
//                new Memory(),
//                new HardDrive(),
//                MODEL_IDENTIFIER,
//                SERIAL_NUMBER,
//                LocalDate.of(2010,8,4),
//                PREINSTALLED_MACOS,
//                DISPLAY_SIZE
//        ));
//        computerList.add(new HackintoshComputer(
//                new MotherBoard(),
//                new Processor(),
//                new Memory(),
//                new HardDrive(),
//                new PcCase(),
//                new PowerSupply(),
//                new VideoCard()
//        ));
//        computerList.add(new HackintoshComputer(
//                new MotherBoard(),
//                new Processor(),
//                new Memory(),
//                new HardDrive(),
//                new PcCase(),
//                new PowerSupply(),
//                new VideoCard()
//        ));
//        when(technicalSpecificationRepository.findAll()).thenReturn(computerList);
//        List<TechnicalSpecification> result = technicalSpecificationService.findAll();
//        assertThat(result).isEqualTo(computerList);
    }
}