package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.Config.ModelMapperConfig;
import ar.com.healthyapple.crm_web.Utils.ClientDtoFactory;
import ar.com.healthyapple.crm_web.Utils.ClientFactory;
import ar.com.healthyapple.crm_web.dto.Client.ClientDto;
import ar.com.healthyapple.crm_web.model.Client.Client;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class ClientDtoConverterTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private static ModelMapper modelMapper;

    private static ClientDtoConverter converter;

    @BeforeAll
     static void setUp() throws Exception {
        modelMapper = new ModelMapperConfig().modelMapper();
        converter = new ClientDtoConverter(modelMapper);
    }

    @Test
     void convertToEntity_ClientToClientDto_ReturnsClientDto() {
        Client client = ClientFactory.makeClient();
        ClientDto result = converter.convertToDto(client);

        assertThat(result.getId()).isEqualTo(client.getId());
        assertThat(result.getMobile()).isEqualTo(client.getMobile());
        assertThat(result.getStartDate()).isEqualTo(client.getStartDate().toString());
        assertThat(result.getFirstName()).isEqualTo(client.getFirstName());
        assertThat(result.getLastName()).isEqualTo(client.getLastName());
        assertThat(result.getEmail()).isEqualTo(client.getEmail());
        assertThat(result.getAddress()).isEqualTo(client.getAddress());
    }

    @Test
     void convertToEntity_NullClientDto_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToDto(null));

    }

    @Test
     void convertToDto_ClientDtoToClient_ReturnsClient() {
        ClientDto clientDto = ClientDtoFactory.makeClientDto();
        Client result = converter.convertToEntity(clientDto);

        assertThat(result.getId()).isEqualTo(clientDto.getId());
        assertThat(result.getMobile()).isEqualTo(clientDto.getMobile());
        assertThat(result.getStartDate()).isEqualTo(clientDto.getStartDate().toString());
        assertThat(result.getFirstName()).isEqualTo(clientDto.getFirstName());
        assertThat(result.getLastName()).isEqualTo(clientDto.getLastName());
        assertThat(result.getEmail()).isEqualTo(clientDto.getEmail());
        assertThat(result.getAddress()).isEqualTo(clientDto.getAddress());
    }

    @Test
     void convertToDto_NullClient_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntity(null));

    }
}