package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.dto.Product.ComponentProfileDto;
import ar.com.healthyapple.crm_web.model.Product.ComponentProfile;
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
public class ComponentProfileDtoConverter {
    private ModelMapper modelMapper;

    @Autowired
    public ComponentProfileDtoConverter(ModelMapper modelMapper) {

        modelMapper.createTypeMap(ComponentProfile.class, ComponentProfileDto.class).addMappings(mapper -> {
            mapper.map(ComponentProfile::getId, ComponentProfileDto::setId);
            mapper.map(ComponentProfile::getType, ComponentProfileDto::setType);
            mapper.map(ComponentProfile::getDescription, ComponentProfileDto::setDescription);
            mapper.map(ComponentProfile::getMultiple, ComponentProfileDto::setMultiple);
            mapper.map(ComponentProfile::getSpecifications, ComponentProfileDto::setSpecifications);
        });

        modelMapper.createTypeMap(ComponentProfileDto.class, ComponentProfile.class).addMappings(mapper -> {
            mapper.map(ComponentProfileDto::getId, ComponentProfile::setId);
            mapper.map(ComponentProfileDto::getType, ComponentProfile::setType);
            mapper.map(ComponentProfileDto::getDescription, ComponentProfile::setDescription);
            mapper.map(ComponentProfileDto::getMultiple, ComponentProfile::setMultiple);
            mapper.map(ComponentProfileDto::getSpecifications, ComponentProfile::setSpecifications);
        });
        this.modelMapper = modelMapper;
    }

    public ComponentProfile convertToEntity(ComponentProfileDto dto) {
        return modelMapper.map(dto, ComponentProfile.class);
    }

    public ComponentProfileDto convertToDto(ComponentProfile entity) {
        return modelMapper.map(entity, ComponentProfileDto.class);
    }

}
