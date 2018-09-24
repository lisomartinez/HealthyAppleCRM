package ar.com.healthyapple.crm_web.dto.Computer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


public class ComputerDtoTest {


    private ObjectMapper objectMapper;

    private HackintoshComputerDto hackintoshComputerDtoRequest;

    @Before
    public void setUp() throws Exception {

        objectMapper = new ObjectMapper();

        hackintoshComputerDtoRequest = new HackintoshComputerDto(
                new MotherBoardDto(),
                new ProcessorDto(),
                new MemoryDto(),
                new HardDriveDto(),
                new PcCaseDto(),
                new PowerSupplyDto(),
                new VideoCardDto()
        );
    }

    @Test
    public void serialize() throws Exception {
        String json = objectMapper.writeValueAsString(hackintoshComputerDtoRequest);
        ComputerDto computerDto = objectMapper.readValue(json, ComputerDto.class);
        System.out.println(json);
        System.out.println(objectMapper.writeValueAsString(computerDto));
        assertThat(hackintoshComputerDtoRequest).isEqualTo(computerDto);
    }
}