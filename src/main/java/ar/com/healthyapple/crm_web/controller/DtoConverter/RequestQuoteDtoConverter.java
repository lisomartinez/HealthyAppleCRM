package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.dto.quote.QuoteDto;
import ar.com.healthyapple.crm_web.dto.quote.RequestQuoteDto;

import ar.com.healthyapple.crm_web.model.Quote.Quote;
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
public class RequestQuoteDtoConverter{

    private ModelMapper modelMapper;

    @Autowired
    public RequestQuoteDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public RequestQuoteDto convertToDto(Quote entity) {
        return modelMapper.map(entity, RequestQuoteDto.class);
    }

    public Quote convertToEntity(RequestQuoteDto dto) {
        return modelMapper.map(dto, Quote.class);
    }

    public QuoteDto convertToQuoteDto(RequestQuoteDto dto) {
        return modelMapper.map(dto, QuoteDto.class);
    }

}
