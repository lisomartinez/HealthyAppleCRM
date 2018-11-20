package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.dto.Product.ProductProfileDto;
import ar.com.healthyapple.crm_web.model.Product.ProductProfile;
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
public class ProductProfileDtoConverter {
    private ModelMapper modelMapper;

    @Autowired
    public ProductProfileDtoConverter(ModelMapper modelMapper) {
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
        this.modelMapper = modelMapper;
    }

    public ProductProfile convertToEntity(ProductProfileDto dto) {
        return modelMapper.map(dto, ProductProfile.class);
    }

    public ProductProfileDto convertToDto(ProductProfile entity) {
        return modelMapper.map(entity, ProductProfileDto.class);
    }


}
