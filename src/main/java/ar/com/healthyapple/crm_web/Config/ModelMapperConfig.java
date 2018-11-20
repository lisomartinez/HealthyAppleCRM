package ar.com.healthyapple.crm_web.Config;


import ar.com.healthyapple.crm_web.dto.Client.ClientDto;
import ar.com.healthyapple.crm_web.dto.Client.ThinClientDto;
import ar.com.healthyapple.crm_web.dto.Product.*;
import ar.com.healthyapple.crm_web.dto.Sale.SaleDto;
import ar.com.healthyapple.crm_web.dto.quote.QuoteDto;
import ar.com.healthyapple.crm_web.dto.quote.QuoteItemDto;
import ar.com.healthyapple.crm_web.dto.quote.RequestQuoteDto;
import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Product.*;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import ar.com.healthyapple.crm_web.model.Quote.QuoteItem;
import ar.com.healthyapple.crm_web.model.Sale.Sale;
import org.modelmapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Product.class, ProductDto.class).addMappings(mapper -> {
            mapper.map(Product::getId, ProductDto::setId);
            mapper.map(Product::getProductType, ProductDto::setProductType);
            mapper.map(Product::getDescription, ProductDto::setDescription);
            mapper.map(Product::getComponents, ProductDto::setComponents);
        });

        modelMapper.createTypeMap(ProductDto.class, Product.class).addMappings(mapper -> {
            mapper.map(ProductDto::getId, Product::setId);
            mapper.map(ProductDto::getProductType, Product::setProductType);
            mapper.map(ProductDto::getDescription, Product::setDescription);
            mapper.map(ProductDto::getComponents, Product::setComponents);
        });

        modelMapper.createTypeMap(Component.class, ComponentDto.class).addMappings(mapper -> {
            mapper.map(Component::getId, ComponentDto::setId);
            mapper.map(Component::getComponentType, ComponentDto::setComponentType);
            mapper.map(Component::getDescription, ComponentDto::setDescription);
            mapper.map(Component::getSpecifications, ComponentDto::setSpecifications);
        });

        modelMapper.createTypeMap(ComponentDto.class, Component.class).addMappings(mapper -> {
            mapper.map(ComponentDto::getId, Component::setId);
            mapper.map(ComponentDto::getDescription, Component::setDescription);
            mapper.map(ComponentDto::getDescription, Component::setDescription);
            mapper.map(ComponentDto::getSpecifications, Component::setSpecifications);
        });

        modelMapper.createTypeMap(ProductType.class, ProductTypeDto.class).addMappings(mapper -> {
            mapper.map(ProductType::getId, ProductTypeDto::setId);
            mapper.map(ProductType::getName, ProductTypeDto::setName);
        });


        modelMapper.createTypeMap(ProductTypeDto.class, ProductType.class).addMappings(mapper -> {
            mapper.map(ProductTypeDto::getId, ProductType::setId);
            mapper.map(ProductTypeDto::getName, ProductType::setName);
        });

        modelMapper.createTypeMap(ComponentType.class, ComponentTypeDto.class).addMappings(mapper -> {
            mapper.map(ComponentType::getId, ComponentTypeDto::setId);
            mapper.map(ComponentType::getName, ComponentTypeDto::setName);
        });

        modelMapper.createTypeMap(ComponentTypeDto.class, ComponentType.class).addMappings(mapper -> {
            mapper.map(ComponentTypeDto::getId, ComponentType::setId);
            mapper.map(ComponentTypeDto::getName, ComponentType::setName);
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

        modelMapper.createTypeMap(Client.class, ClientDto.class).addMappings(mapper -> {
            mapper.map(Client::getId, ClientDto::setId);
            mapper.map(Client::getMobile, ClientDto::setMobile);
            mapper.map(Client::getStartDate, ClientDto::setStartDate);
            mapper.map(Client::getFirstName, ClientDto::setFirstName);
            mapper.map(Client::getLastName, ClientDto::setLastName);
            mapper.map(Client::getEmail, ClientDto::setEmail);
            mapper.map(Client::getAddress, ClientDto::setAddress);
            mapper.map(Client::getProducts, ClientDto::setProducts);
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
            mapper.map(Quote::getProducts, QuoteDto::setProducts);
        });

        modelMapper.createTypeMap(QuoteDto.class, Quote.class).addMappings(mapper -> {
            mapper.map(QuoteDto::getId, Quote::setId);
            mapper.map(QuoteDto::getNumber, Quote::setNumber);
            mapper.map(QuoteDto::getVersion, Quote::setVersion);
            mapper.map(QuoteDto::getDescription, Quote::setDescription);
            mapper.map(QuoteDto::getStatus, Quote::setStatus);
            mapper.map(QuoteDto::getCreated, Quote::setCreated);
            mapper.map(QuoteDto::getCost, Quote::setCost);
            mapper.map(QuoteDto::getPrice, Quote::setPrice);
            mapper.map(QuoteDto::getProducts, Quote::setProducts);
        });

        modelMapper.createTypeMap(QuoteItem.class, QuoteItemDto.class).addMappings(mapper -> {
            mapper.map(QuoteItem::getId, QuoteItemDto::setId);
            mapper.map(QuoteItem::getDescription, QuoteItemDto::setDescription);
            mapper.map(QuoteItem::getComponent, QuoteItemDto::setComponent);
            mapper.map(QuoteItem::getCost, QuoteItemDto::setCost);
            mapper.map(QuoteItem::getPrice, QuoteItemDto::setPrice);
        });
        modelMapper.createTypeMap(QuoteItemDto.class, QuoteItem.class).addMappings(mapper -> {
            mapper.map(QuoteItemDto::getId, QuoteItem::setId);
            mapper.map(QuoteItemDto::getDescription, QuoteItem::setDescription);
            mapper.map(QuoteItemDto::getComponent, QuoteItem::setComponent);
            mapper.map(QuoteItemDto::getCost, QuoteItem::setCost);
            mapper.map(QuoteItemDto::getPrice, QuoteItem::setPrice);
        });

        modelMapper.createTypeMap(RequestQuoteDto.class, Quote.class).addMappings(mapper -> {
            mapper.map(RequestQuoteDto::getDescription, Quote::setDescription);
        });

        modelMapper.createTypeMap(RequestQuoteDto.class, QuoteDto.class).addMappings(mapper -> {
            mapper.map(RequestQuoteDto::getDescription, QuoteDto::setDescription);
        });

        modelMapper.createTypeMap(Quote.class, RequestQuoteDto.class).addMappings(mapper -> {
            mapper.map(Quote::getDescription, RequestQuoteDto::setDescription);
        });


        modelMapper.createTypeMap(Sale.class, SaleDto.class).addMappings(mapper -> {
            mapper.map(Sale::getId, SaleDto::setId);
            mapper.map(Sale::getDescription, SaleDto::setDescription);
            mapper.map(Sale::getState, SaleDto::setState);
            mapper.map(Sale::getStartDate, SaleDto::setStartDate);
            mapper.map(Sale::getQuote, SaleDto::setQuote);
        });

        modelMapper.createTypeMap(SaleDto.class, Sale.class).addMappings(mapper -> {
            mapper.map(SaleDto::getId, Sale::setId);
            mapper.map(SaleDto::getDescription, Sale::setDescription);
            mapper.map(SaleDto::getState, Sale::setState);
            mapper.map(SaleDto::getStartDate, Sale::setStartDate);
            mapper.map(SaleDto::getQuote, Sale::setQuote);
        });

        modelMapper.createTypeMap(StateBasedProduct.class, StateBasedProductDto.class).addMappings(mapper -> {
            mapper.map(StateBasedProduct::getId, StateBasedProductDto::setId);
            mapper.map(StateBasedProduct::getState, StateBasedProductDto::setState);
            mapper.map(StateBasedProduct::getProduct, StateBasedProductDto::setProduct);
        });
        modelMapper.createTypeMap(StateBasedProductDto.class, StateBasedProduct.class).addMappings(mapper -> {
            mapper.map(StateBasedProductDto::getId, StateBasedProduct::setId);
            mapper.map(StateBasedProductDto::getState, StateBasedProduct::setState);
            mapper.map(StateBasedProductDto::getProduct, StateBasedProduct::setProduct);
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

        modelMapper.createTypeMap(Client.class, ThinClientDto.class).addMappings(mapper -> {
            mapper.map(Client::getId, ThinClientDto::setId);
            mapper.map(Client::getMobile, ThinClientDto::setMobile);
            mapper.map(Client::getStartDate, ThinClientDto::setStartDate);
            mapper.map(Client::getFirstName, ThinClientDto::setFirstName);
            mapper.map(Client::getLastName, ThinClientDto::setLastName);
            mapper.map(Client::getEmail, ThinClientDto::setEmail);
            mapper.map(Client::getAddress, ThinClientDto::setAddress);
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
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                return LocalDateTime.parse(source, format);
            }
        };

        modelMapper.createTypeMap(String.class, LocalDateTime.class);
        modelMapper.addConverter(toStringDateTime);
        modelMapper.getTypeMap(String.class, LocalDateTime.class).setProvider(localDateTimeProvider);


        return modelMapper;
    }
}
