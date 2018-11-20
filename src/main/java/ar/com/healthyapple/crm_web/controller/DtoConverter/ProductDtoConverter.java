package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.dto.Product.ProductDto;
import ar.com.healthyapple.crm_web.dto.Product.ProductTypeDto;
import ar.com.healthyapple.crm_web.model.Product.Product;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Component
public class ProductDtoConverter {

    private ModelMapper modelMapper;

    @Autowired
    public ProductDtoConverter(ModelMapper modelMapper) {
//        modelMapper.createTypeMap(Product.class, ProductDto.class).addMappings(mapper -> {
//            mapper.map(Product::getId, ProductDto::setId);
//            mapper.map(Product::getProductType, ProductDto::setProductType);
//            mapper.map(product -> productTypeDtoConverter.convertToDto(product.getProductType()), ProductDto::setProductType);
//            mapper.map(Product::getDescription, ProductDto::setDescription);
//            mapper.map(Product::getComponents, ProductDto::setComponents);
//            mapper.map(product -> product.getComponents()
//                    .stream()
//                    .map(componentDtoConverter::convertToDto)
//                    .collect(Collectors.toList()), ProductDto::setComponents);
//        });
//
//        modelMapper.createTypeMap(ProductDto.class, Product.class).addMappings(mapper -> {
//            mapper.map(ProductDto::getId, Product::setId);
//            mapper.map(product -> productTypeDtoConverter.convertToEntity(product.getProductType()), Product::setProductType);
//            mapper.map(ProductDto::getDescription, Product::setDescription);
//            mapper.map(productDto -> productDto.getComponents()
//                    .stream()
//                    .map(componentDtoConverter::convertToEntity)
//                    .collect(Collectors.toList()), Product::setComponents);
//        });

        this.modelMapper = modelMapper;

    }

    public Product convertToEntity(ProductDto dto) {
        return modelMapper.map(dto, Product.class);
    }

    public ProductDto convertToDto(Product entity) {
        return modelMapper.map(entity, ProductDto.class);

    }
}
