package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.dto.Sale.SaleItemDto;
import ar.com.healthyapple.crm_web.model.Sale.SaleItem;
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
public class SaleItemDtoConverter {


    private ModelMapper modelMapper;

    @Autowired
    public SaleItemDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SaleItemDto convertToDto(SaleItem entity) {
        return modelMapper.map(entity, SaleItemDto.class);
    }

    public SaleItem convertToEntity(SaleItemDto dto) {
        return modelMapper.map(dto, SaleItem.class);
    }
}
