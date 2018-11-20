package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.dto.Product.SpecificationDto;
import ar.com.healthyapple.crm_web.model.Product.Specification;
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
public class SpecificationDtoConverter {

    private ModelMapper modelMapper;

    @Autowired
    public SpecificationDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public SpecificationDto convertToDto(Specification entity) {
        return modelMapper.map(entity, SpecificationDto.class);
    }

    public Specification convertToEntity(SpecificationDto dto) {
        return modelMapper.map(dto, Specification.class);
    }

}
