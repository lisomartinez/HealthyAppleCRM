package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.dto.Product.ComponentTypeDto;
import ar.com.healthyapple.crm_web.model.Product.ComponentType;
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
public class ComponentTypeDtoConverter {

    private ModelMapper modelMapper;

    @Autowired
    public ComponentTypeDtoConverter(ModelMapper modelMapper) {


//        modelMapper.createTypeMap(ComponentType.class, ComponentTypeDto.class).addMappings(mapper -> {
//            mapper.map(ComponentType::getId, ComponentTypeDto::setId);
//            mapper.map(ComponentType::getName, ComponentTypeDto::setName);
//        });
//
//        modelMapper.createTypeMap(ComponentTypeDto.class, ComponentType.class).addMappings(mapper -> {
//            mapper.map(ComponentTypeDto::getId, ComponentType::setId);
//            mapper.map(ComponentTypeDto::getName, ComponentType::setName);
//        });

        this.modelMapper = modelMapper;
    }

    public ComponentType convertToEntity(ComponentTypeDto dto) {
        return modelMapper.map(dto, ComponentType.class);
    }

    public ComponentTypeDto convertToDto(ComponentType entity) {
        return modelMapper.map(entity, ComponentTypeDto.class);
    }
}
