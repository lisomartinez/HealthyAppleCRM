package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.dto.Product.ProductTypeDto;
import ar.com.healthyapple.crm_web.model.Product.ProductType;
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
public class ProductTypeDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public ProductTypeDtoConverter(ModelMapper modelMapper) {
//        modelMapper.createTypeMap(ProductType.class, ProductTypeDto.class).addMappings(mapper -> {
//            mapper.map(ProductType::getId, ProductTypeDto::setId);
//            mapper.map(ProductType::getName, ProductTypeDto::setName);
//        });
//
//
//        modelMapper.createTypeMap(ProductTypeDto.class, ProductType.class).addMappings(mapper -> {
//            mapper.map(ProductTypeDto::getId, ProductType::setId);
//            mapper.map(ProductTypeDto::getName, ProductType::setName);
//        });

        this.modelMapper = modelMapper;
    }

    public ProductTypeDto convertToDto(ProductType entity) {
        return modelMapper.map(entity, ProductTypeDto.class);
    }

    public ProductType convertToEntity(ProductTypeDto dto) {
        return modelMapper.map(dto, ProductType.class);
    }
}
