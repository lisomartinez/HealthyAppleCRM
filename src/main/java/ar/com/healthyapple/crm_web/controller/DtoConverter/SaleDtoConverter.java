package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.dto.Sale.SaleDto;
import ar.com.healthyapple.crm_web.model.Sale.Sale;
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
public class SaleDtoConverter {

    private ModelMapper modelMapper;

    @Autowired
    public SaleDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Sale convertToEntity(SaleDto dto) {
        return modelMapper.map(dto, Sale.class);
    }

    public SaleDto convertToDto(Sale entity) {
        return modelMapper.map(entity, SaleDto.class);
    }
}
