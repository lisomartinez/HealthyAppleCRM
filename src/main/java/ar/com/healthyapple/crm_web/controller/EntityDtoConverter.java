package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.dto.Computer.ComponentDto;
import ar.com.healthyapple.crm_web.dto.Computer.ComputerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Component
public class EntityDtoConverter {

    private ModelMapper modelMapper;

    @Autowired
    public EntityDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <S extends ComponentDto, D extends Component> D convertToEntity(S dto, Class<D> EntityClassType) {
        return modelMapper.map(dto, EntityClassType);
    }

    public <S extends Component, D extends ComponentDto> D convertToDto(S entity, Class<D> dtoClassType) {
        return modelMapper.map(entity, dtoClassType);
    }


    public <S extends ComputerDto, D extends Computer> D convertToEntity(S dto, Class<D> EntityClassType) {
        return modelMapper.map(dto, EntityClassType);
    }

    public <S extends Computer, D extends ComputerDto> D convertToDto(S entity, Class<D> dtoClassType) {
        return modelMapper.map(entity, dtoClassType);
    }

}
