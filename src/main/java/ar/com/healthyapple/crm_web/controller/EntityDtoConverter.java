package ar.com.healthyapple.crm_web.controller;

import ar.com.healthyapple.crm_web.dto.Client.ClientDto;
import ar.com.healthyapple.crm_web.dto.Client.ThinClientDto;
import ar.com.healthyapple.crm_web.dto.Product.*;
import ar.com.healthyapple.crm_web.dto.quote.QuoteDto;
import ar.com.healthyapple.crm_web.dto.quote.RequestQuoteDto;
import ar.com.healthyapple.crm_web.dto.SaleDto;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Product.*;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Sale.Sale;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@org.springframework.stereotype.Component
public class EntityDtoConverter {

    private ModelMapper modelMapper;


    @Autowired
    public EntityDtoConverter(ModelMapper modelMapper) {

        modelMapper.createTypeMap(Client.class, ClientDto.class).addMappings(mapper -> {
            mapper.map(Client::getId, ClientDto::setId);
            mapper.map(Client::getMobile, ClientDto::setMobile);
            mapper.map(Client::getStartDate, ClientDto::setStartDate);
            mapper.map(Client::getFirstName, ClientDto::setFirstName);
            mapper.map(Client::getLastName, ClientDto::setLastName);
            mapper.map(Client::getEmail, ClientDto::setEmail);
            mapper.map(Client::getAddress, ClientDto::setAddress);
            mapper.map(Client::getProducts, ClientDto::setProducts);
            mapper.map(Client::getServices, ClientDto::setServices);
        });

        modelMapper.createTypeMap(ClientDto.class, Client.class).addMappings(mapper -> {
            mapper.map(ClientDto::getId, Client::setId);
            mapper.map(ClientDto::getMobile, Client::setMobile);
            mapper.map(ClientDto::getStartDate, Client::setStartDate);
            mapper.map(ClientDto::getFirstName, Client::setFirstName);
            mapper.map(ClientDto::getLastName, Client::setLastName);
            mapper.map(ClientDto::getEmail, Client::setEmail);
            mapper.map(ClientDto::getAddress, Client::setAddress);
            mapper.map(ClientDto::getProducts, Client::setProducts);
            mapper.map(ClientDto::getServices, Client::setServices);
        });

        modelMapper.createTypeMap(Client.class, ThinClientDto.class).addMappings(mapper -> {
            mapper.map(Client::getId, ThinClientDto::setId);
            mapper.map(Client::getMobile, ThinClientDto::setMobile);
            mapper.map(Client::getStartDate, ThinClientDto::setStartDate);
            mapper.map(Client::getFirstName, ThinClientDto::setFirstName);
            mapper.map(Client::getLastName, ThinClientDto::setLastName);
            mapper.map(Client::getEmail, ThinClientDto::setEmail);
            mapper.map(Client::getAddress, ThinClientDto::setAddress);
        });

        modelMapper.createTypeMap(ThinClientDto.class, Client.class).addMappings(mapper -> {
            mapper.map(ThinClientDto::getId, Client::setId);
            mapper.map(ThinClientDto::getMobile, Client::setMobile);
            mapper.map(ThinClientDto::getStartDate, Client::setStartDate);
            mapper.map(ThinClientDto::getFirstName, Client::setFirstName);
            mapper.map(ThinClientDto::getLastName, Client::setLastName);
            mapper.map(ThinClientDto::getEmail, Client::setEmail);
            mapper.map(ThinClientDto::getAddress, Client::setAddress);
        });

        modelMapper.createTypeMap(Product.class, ProductDto.class).addMappings(mapper -> {
            mapper.map(Product::getId, ProductDto::setId);
            mapper.map(Product::getProductType, ProductDto::setProductType);
            mapper.map(Product::getDescription, ProductDto::setDescription);
            mapper.map(Product::getComponents, ProductDto::setComponents);
        });

        modelMapper.createTypeMap(Product.class, ProductWithoutProfileDto.class).addMappings(mapper -> {
            mapper.map(Product::getId, ProductWithoutProfileDto::setId);
            mapper.map(Product::getProductType, ProductWithoutProfileDto::setProductType);
            mapper.map(Product::getDescription, ProductWithoutProfileDto::setDescription);
            mapper.map(Product::getComponents, ProductWithoutProfileDto::setComponents);
        });

        modelMapper.createTypeMap(ProductDto.class, Product.class).addMappings(mapper -> {
            mapper.map(ProductDto::getId, Product::setId);
            mapper.map(ProductDto::getProductType, Product::setProductType);
            mapper.map(ProductDto::getDescription, Product::setDescription);
            mapper.map(ProductDto::getComponents, Product::setComponents);
        });

        modelMapper.createTypeMap(ProductProfile.class, ProductProfileDto.class).addMappings(mapper -> {
            mapper.map(ProductProfile::getId, ProductProfileDto::setId);
            mapper.map(ProductProfile::getType, ProductProfileDto::setType);
            mapper.map(ProductProfile::getDescription, ProductProfileDto::setDescription);
            mapper.map(ProductProfile::getComponents, ProductProfileDto::setComponents);
        });

        modelMapper.createTypeMap(ProductProfileDto.class, ProductProfile.class).addMappings(mapper -> {
            mapper.map(ProductProfileDto::getId, ProductProfile::setId);
            mapper.map(ProductProfileDto::getType, ProductProfile::setType);
            mapper.map(ProductProfileDto::getDescription, ProductProfile::setDescription);
            mapper.map(ProductProfileDto::getComponents, ProductProfile::setComponents);
        });

        modelMapper.createTypeMap(ProductType.class, ProductTypeDto.class).addMappings(mapper -> {
            mapper.map(ProductType::getId, ProductTypeDto::setId);
            mapper.map(ProductType::getName, ProductTypeDto::setName);
        });

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



        modelMapper.createTypeMap(Component.class, ComponentDto.class).addMappings(mapper -> {
            mapper.map(Component::getId, ComponentDto::setId);
            mapper.map(Component::getComponentType, ComponentDto::setComponentType);
            mapper.map(Component::getDescription, ComponentDto::setDescription);
            mapper.map(Component::getSpecifications, ComponentDto::setSpecifications);
        });

        modelMapper.createTypeMap(ComponentType.class, ComponentTypeDto.class).addMappings(mapper -> {
            mapper.map(ComponentType::getId, ComponentTypeDto::setId);
            mapper.map(ComponentType::getName, ComponentTypeDto::setName);
        });

        modelMapper.createTypeMap(ComponentTypeDto.class, ComponentType.class).addMappings(mapper -> {
            mapper.map(ComponentTypeDto::getId, ComponentType::setId);
            mapper.map(ComponentTypeDto::getName, ComponentType::setName);
        });


        modelMapper.createTypeMap(ComponentDto.class, Component.class).addMappings(mapper -> {
            mapper.map(ComponentDto::getId, Component::setId);
            mapper.map(ComponentDto::getDescription, Component::setDescription);
            mapper.map(ComponentDto::getComponentType, Component::setComponentType);
            mapper.map(ComponentDto::getSpecifications, Component::setSpecifications);
        });

        modelMapper.createTypeMap(RequestQuoteDto.class, Quote.class).addMappings(mapper -> {
            mapper.map(RequestQuoteDto::getNumber, Quote::setNumber);
            mapper.map(RequestQuoteDto::getClient, Quote::setClient);
            mapper.map(RequestQuoteDto::getDescription, Quote::setDescription);
        });

        modelMapper.createTypeMap(Quote.class, QuoteDto.class).addMappings(mapper -> {
            mapper.map(Quote::getId, QuoteDto::setId);
            mapper.map(Quote::getNumber, QuoteDto::setNumber);
            mapper.map(Quote::getVersion, QuoteDto::setVersion);
            mapper.map(Quote::getDescription, QuoteDto::setDescription);
            mapper.map(Quote::getStatus, QuoteDto::setStatus);
            mapper.map(Quote::getCreated, QuoteDto::setCreated);
            mapper.map(Quote::getCost, QuoteDto::setCost);
            mapper.map(Quote::getPrice, QuoteDto::setPrice);
            mapper.map(Quote::getClient, QuoteDto::setClient);
            mapper.map(Quote::getProductServices, QuoteDto::setServices);
        });

        modelMapper.createTypeMap(Specification.class, SpecificationDto.class).addMappings(mapper -> {
            mapper.map(Specification::getId, SpecificationDto::setId);
            mapper.map(Specification::getName, SpecificationDto::setName);
            mapper.map(Specification::getDescription, SpecificationDto::setDescription);
        });

        modelMapper.createTypeMap(SpecificationDto.class, Specification.class).addMappings(mapper -> {
            mapper.map(SpecificationDto::getId, Specification::setId);
            mapper.map(SpecificationDto::getName, Specification::setName);
            mapper.map(SpecificationDto::getDescription, Specification::setDescription);
        });

        Provider<LocalDate> localDateProvider = new AbstractProvider<>() {
            @Override
            public LocalDate get() {
                return LocalDate.now();
            }
        };

        Converter<String, LocalDate> toStringDate = new AbstractConverter<>() {
            @Override
            protected LocalDate convert(String source) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return LocalDate.parse(source, format);
            }
        };
        modelMapper.createTypeMap(String.class, LocalDate.class);
        modelMapper.addConverter(toStringDate);
        modelMapper.getTypeMap(String.class, LocalDate.class).setProvider(localDateProvider);


        Provider<LocalDateTime> localDateTimeProvider = new AbstractProvider<>() {
            @Override
            public LocalDateTime get() {
                return LocalDateTime.now();
            }
        };

        Converter<String, LocalDateTime> toStringDateTime = new AbstractConverter<>() {
            @Override
            protected LocalDateTime convert(String source) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm");
                LocalDateTime localDateTime = LocalDateTime.parse(source, format);
                return localDateTime;
            }
        };

        modelMapper.createTypeMap(String.class, LocalDateTime.class);
        modelMapper.addConverter(toStringDateTime);
        modelMapper.getTypeMap(String.class, LocalDateTime.class).setProvider(localDateTimeProvider);

        this.modelMapper = modelMapper;
    }

    public Component convertToEntity(ComponentDto dto, Class<Component> EntityClassType) {
        return modelMapper.map(dto, EntityClassType);
    }

    public ComponentDto convertToDto(Component entity, Class<ComponentDto> dtoClassType) {
        return modelMapper.map(entity, dtoClassType);
    }

    public ComponentTypeDto convertToDto(ComponentType entity, Class<ComponentTypeDto> dtoClassType) {
        return modelMapper.map(entity, dtoClassType);
    }


    public Client convertToEntity(ClientDto dto, Class<Client> EntityClassType) {
        return modelMapper.map(dto, EntityClassType);
    }

    public ClientDto convertToDto(Client entity, Class<ClientDto> dtoClassType) {
        return modelMapper.map(entity, dtoClassType);
    }

    public ThinClientDto convertToThinClientDto(Client entity, Class<ThinClientDto> DtoClassType) {
        return modelMapper.map(entity, DtoClassType);
    }


    public Client convertThinClientToEntity(ThinClientDto dto, Class<Client> EntityClassType) {
        return modelMapper.map(dto, EntityClassType);
    }

    public Product convertToEntity(ProductDto dto, Class<Product> EntityClassType) {
        return modelMapper.map(dto, EntityClassType);
    }

    public ProductDto convertToDto(Product entity, Class<ProductDto> dtoClassType) {
        return modelMapper.map(entity, dtoClassType);

    }

    public Sale convertToEntity(SaleDto dto, Class<Sale> EntityClassType) {
        return modelMapper.map(dto, EntityClassType);
    }

    public SaleDto convertToDto(Sale entity, Class<SaleDto> dtoClassType) {
        return modelMapper.map(entity, dtoClassType);
    }


    public Quote convertToEntity(RequestQuoteDto dto, Class<Quote> EntityClassType) {
        return modelMapper.map(dto, EntityClassType);

    }

    public QuoteDto convertToDto(Quote entity, Class<QuoteDto> dtoClassType) {
        return modelMapper.map(entity, dtoClassType);
    }

    public ProductProfileDto convertToDto(ProductProfile entity, Class<ProductProfileDto> dtoClassType) {
        return modelMapper.map(entity, dtoClassType);
    }

    public ProductProfile convertToEntity(ProductProfileDto dto, Class<ProductProfile> EntityClassType) {
        return modelMapper.map(dto, EntityClassType);
    }


    public ProductWithoutProfileDto convertToThinProductDto(Product entity, Class<ProductWithoutProfileDto> dtoClassType) {
        return modelMapper.map(entity, dtoClassType);
    }

    public ComponentProfileDto convertToDto(ComponentProfile entity, Class<ComponentProfileDto> dtoClassType) {
        return modelMapper.map(entity, dtoClassType);
    }

    public ComponentProfile convertToEntity(ComponentProfileDto dto, Class<ComponentProfile> EntityClassType) {
        return modelMapper.map(dto, EntityClassType);
    }


    public SpecificationDto convertToDto(Specification entity, Class<SpecificationDto> dtoClassType) {
        return modelMapper.map(entity, dtoClassType);
    }


    public Specification convertToEntity(SpecificationDto dto, Class<Specification> EntityClassType) {
        return modelMapper.map(dto, EntityClassType);
    }

    public ComponentType convertToEntity(ComponentTypeDto dto, Class<ComponentType> EntityClassType) {
        return modelMapper.map(dto, EntityClassType);
    }



}
