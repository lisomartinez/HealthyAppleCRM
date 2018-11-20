package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.dto.Client.ClientDto;
import ar.com.healthyapple.crm_web.model.Client.Client;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientDtoConverter {

    private ModelMapper modelMapper;

    @Autowired
    public ClientDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Client convertToEntity(ClientDto dto) {
        return modelMapper.map(dto, Client.class);
    }

    public ClientDto convertToDto(Client entity) {
        return modelMapper.map(entity, ClientDto.class);
    }

}
