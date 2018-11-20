package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.dto.Product.ComponentDto;
import ar.com.healthyapple.crm_web.dto.Product.ComponentTypeDto;
import ar.com.healthyapple.crm_web.model.Product.Component;
import ar.com.healthyapple.crm_web.model.Product.ComponentType;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@org.springframework.stereotype.Component
public class ComponentDtoConverter {

    private ModelMapper modelMapper;

    @Autowired
    public ComponentDtoConverter(ModelMapper modelMapper) {
//        modelMapper.createTypeMap(Component.class, ComponentDto.class).addMappings(mapper -> {
//            mapper.map(Component::getId, ComponentDto::setId);
//            mapper.map(component -> componentTypeDtoConverter.convertToDto(component.getComponentType()), ComponentDto::setComponentType);
//            mapper.map(Component::getDescription, ComponentDto::setDescription);
////            mapper.map(component -> component.getSpecifications()
////                    .stream()
////                    .map(specificationDtoConverter::convertToDto)
////                    .collect(Collectors.toList()), ComponentDto::setSpecifications);
//        });
//
//        modelMapper.createTypeMap(ComponentDto.class, Component.class).addMappings(mapper -> {
//            mapper.map(ComponentDto::getId, Component::setId);
//            mapper.map(ComponentDto::getDescription, Component::setDescription);
//            mapper.map(component -> componentTypeDtoConverter.convertToEntity(component.getComponentType()), Component::setComponentType);
////            mapper.map(componentDto -> componentDto.getSpecifications()
////                    .stream()
////                    .map(specificationDtoConverter::convertToEntity)
////                    .collect(Collectors.toList()), Component::setSpecifications);
//        });

        this.modelMapper = modelMapper;
    }

    public Component convertToEntity(ComponentDto dto) {
        return modelMapper.map(dto, Component.class);
    }

    public ComponentDto convertToDto(Component entity) {
        return modelMapper.map(entity, ComponentDto.class);
    }
}
