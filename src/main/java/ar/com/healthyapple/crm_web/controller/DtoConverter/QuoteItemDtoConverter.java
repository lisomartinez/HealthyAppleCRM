package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.dto.quote.QuoteItemDto;
import ar.com.healthyapple.crm_web.model.Quote.QuoteItem;
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
public class QuoteItemDtoConverter {

    private ModelMapper modelMapper;


    @Autowired
    public QuoteItemDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public QuoteItemDto convertToDto(QuoteItem entity) {
        return modelMapper.map(entity, QuoteItemDto.class);
    }

    public QuoteItem convertToEntity(QuoteItemDto dto) {
        return modelMapper.map(dto, QuoteItem.class);
    }
}
