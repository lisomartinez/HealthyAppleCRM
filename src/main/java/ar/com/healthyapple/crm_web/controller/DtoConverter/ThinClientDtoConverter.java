package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.dto.Client.ClientDto;
import ar.com.healthyapple.crm_web.dto.Client.ThinClientDto;
import ar.com.healthyapple.crm_web.model.Client.Client;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Component
public class ThinClientDtoConverter {
    private ModelMapper modelMapper;

    @Autowired
    public ThinClientDtoConverter(ModelMapper modelMapper) {
              this.modelMapper = modelMapper;
    }

    public Client convertToEntity(ThinClientDto dto) {
        return modelMapper.map(dto, Client.class);
    }

    public ThinClientDto convertToDto(Client entity) {
        return modelMapper.map(entity, ThinClientDto.class);
    }
}
