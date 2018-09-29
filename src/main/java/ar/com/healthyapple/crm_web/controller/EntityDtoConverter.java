package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.dto.Computer.*;
import ar.com.healthyapple.crm_web.model.*;
import ar.com.healthyapple.crm_web.model.Client.Client;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Component
public class EntityDtoConverter {

    private ModelMapper modelMapper;

    @Autowired
    private ThinClientPageDto clientPageDto;

    @Autowired
    public EntityDtoConverter(ModelMapper modelMapper) {

        modelMapper.createTypeMap(Client.class, ClientDto.class).addMappings(mapper -> {
            mapper.map(Client::getMobile, ClientDto::setMobile);
            mapper.map(Client::getStartDate, ClientDto::setAddress);
            mapper.map(Client::getFirstName, ClientDto::setFirstName);
            mapper.map(Client::getLastName, ClientDto::setLastName);
            mapper.map(Client::getEmail, ClientDto::setEmail);
            mapper.map(Client::getAddress, ClientDto::setAddress);
            mapper.map(Client::getProducts, ClientDto::setProducts);
            mapper.map(Client::getServices, ClientDto::setServices);
        });

        modelMapper.createTypeMap(Product.class, ProductDto.class).addMappings(mapper -> {
            mapper.map(Product::getId, ProductDto::setId);
            mapper.map(Product::getName, ProductDto::setName);
            mapper.map(Product::getDescription, ProductDto::setDescription);
            mapper.map(Product::getSpecs, ProductDto::setSpecifications);
        });

        modelMapper.createTypeMap(TechnicalSpecification.class, TechnicalSpecificationDto.class).addMappings(mapper -> {
            mapper.map(TechnicalSpecification::getId, TechnicalSpecificationDto::setId);
            mapper.map(TechnicalSpecification::getComponents, TechnicalSpecificationDto::setComponents);
            mapper.map(TechnicalSpecification::getTechnicalSpecificationType, TechnicalSpecificationDto::setTechnicalSpecificationType);
        });


            modelMapper.createTypeMap(Component.class, ComponentDto.class).addMappings(mapper -> {
            mapper.map(Component::getId, ComponentDto::setId);
            mapper.map(Component::getName, ComponentDto::setName);
            mapper.map(Component::getSpecifications, ComponentDto::setSpecifications);
        });


            this.modelMapper = modelMapper;
    }

    public <S extends ComponentDto, D extends Component> D convertToEntity(S dto, Class<D> EntityClassType) {
        return modelMapper.map(dto, EntityClassType);
    }

    public <S extends Component, D extends ComponentDto> D convertToDto(S entity, Class<D> dtoClassType) {
        return modelMapper.map(entity, dtoClassType);
    }

    public <S extends ComponentType, D extends ComponentTypeDto> D convertToDto(S entity, Class<D> dtoClassType) {
        return modelMapper.map(entity, dtoClassType);
    }

    public <S extends TechnicalSpecificationType, D extends TechnicalSpecificationTypeDto> D convertToDto(S entity, Class<D> dtoClassType) {
        return modelMapper.map(entity, dtoClassType);
    }

    public <S extends Specification, D extends SpecificationDto> D convertToDto(S entity, Class<D> dtoClassType) {
        return modelMapper.map(entity, dtoClassType);
    }

    public Client convertToEntity( ClientDto dto, Class<Client> EntityClassType) {
        return modelMapper.map(dto, EntityClassType);
    }

    public ClientDto convertToDto(Client entity, Class<ClientDto> dtoClassType) {
        return modelMapper.map(entity, dtoClassType);
    }

    public ThinClientDto convertToThinClientDto(Client entity, Class<ThinClientDto> DtoClassType) {
        return modelMapper.map(entity, DtoClassType);
    }



}
