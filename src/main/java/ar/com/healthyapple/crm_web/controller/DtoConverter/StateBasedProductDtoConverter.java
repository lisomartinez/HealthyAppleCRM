package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.dto.Product.StateBasedProductDto;
import ar.com.healthyapple.crm_web.model.Product.StateBasedProduct;
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
public class StateBasedProductDtoConverter {

    private ModelMapper modelMapper;

    @Autowired
    public StateBasedProductDtoConverter(ModelMapper modelMapper) {
            this.modelMapper = modelMapper;
    }

    public StateBasedProductDto convertToDto(StateBasedProduct entity) {
        return modelMapper.map(entity, StateBasedProductDto.class);
    }

    public StateBasedProduct convertToEntity(StateBasedProductDto dto) {
        return modelMapper.map(dto, StateBasedProduct.class);
    }
}
