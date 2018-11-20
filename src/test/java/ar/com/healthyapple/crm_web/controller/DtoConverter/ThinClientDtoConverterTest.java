package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.Config.ModelMapperConfig;
import ar.com.healthyapple.crm_web.Utils.ClientFactory;
import ar.com.healthyapple.crm_web.Utils.ThinClientDtoFactory;
import ar.com.healthyapple.crm_web.dto.Client.ThinClientDto;
import ar.com.healthyapple.crm_web.model.Client.Client;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
class ThinClientDtoConverterTest {

    private static ModelMapperConfig modelMapperConfig;

    private static ThinClientDtoConverter converter;


    @BeforeAll
    static void setUp() throws Exception {
        modelMapperConfig = new ModelMapperConfig();
        ModelMapper modelMapper = modelMapperConfig.modelMapper();
        converter = new ThinClientDtoConverter(modelMapper);

    }

    @Test
    void convertToEntity() {
        ThinClientDto thinClientDto = ThinClientDtoFactory.makeThinClientDto();
        Client result = converter.convertToEntity(thinClientDto);

        assertThat(result.getId()).isEqualTo(thinClientDto.getId());
        assertThat(result.getMobile()).isEqualTo(thinClientDto.getMobile());
        assertThat(result.getFirstName()).isEqualTo(thinClientDto.getFirstName());
        assertThat(result.getLastName()).isEqualTo(thinClientDto.getLastName());
        assertThat(result.getEmail()).isEqualTo(thinClientDto.getEmail());
        assertThat(result.getAddress()).isEqualTo(thinClientDto.getAddress());
        assertThat(result.getStartDate()).isEqualTo(thinClientDto.getStartDate().toString());
    }

    @Test
     void convertToEntityWithNull() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntity(null));
    }

    @Test
     void convertToDto() {
        Client client = ClientFactory.makeClient();
        ThinClientDto result = converter.convertToDto(client);
        assertThat(result.getId()).isEqualTo(client.getId());
        assertThat(result.getMobile()).isEqualTo(client.getMobile());
        assertThat(result.getFirstName()).isEqualTo(client.getFirstName());
        assertThat(result.getLastName()).isEqualTo(client.getLastName());
        assertThat(result.getEmail()).isEqualTo(client.getEmail());
        assertThat(result.getAddress()).isEqualTo(client.getAddress().toString());
        assertThat(result.getStartDate()).isEqualTo(client.getStartDate().toString());
    }

    @Test
     void convertToDtoWithNull() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntity(null));
    }

}