package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.dto.Product.StateBasedProductDto;
import ar.com.healthyapple.crm_web.dto.quote.QuoteDto;
import ar.com.healthyapple.crm_web.dto.quote.QuoteItemDto;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import lombok.*;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;


@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Component
public class QuoteDtoConverter {

    private ModelMapper modelMapper;

    @Autowired
    public QuoteDtoConverter(ModelMapper modelMapper) {

//        modelMapper.createTypeMap(Quote.class, QuoteDto.class).addMappings(mapper -> {
//            mapper.map(Quote::getId, QuoteDto::setId);
//            mapper.map(Quote::getNumber, QuoteDto::setNumber);
//            mapper.map(Quote::getVersion, QuoteDto::setVersion);
//            mapper.map(Quote::getDescription, QuoteDto::setDescription);
//            mapper.map(Quote::getStatus, QuoteDto::setStatus);
//            mapper.map(Quote::getCreated, QuoteDto::setCreated);
//            mapper.map(Quote::getCost, QuoteDto::setCost);
//            mapper.map(Quote::getPrice, QuoteDto::setPrice);
//            mapper.map(Quote::getClientId, QuoteDto::setClientId);
//            mapper.map(Quote::getProducts, QuoteDto::setProducts);
//            mapper.map(Quote::getItems, QuoteDto::setItems);
//
////            mapper.map(quote -> clientDtoConverter.convertToDto(quote.getClientId()) , QuoteDto::setClientId);
////            mapper.map(quote -> quote.getProducts()
////                    .stream()
////                    .map(stateBasedProductDtoConverter::convertToDto)
////                    .collect(Collectors.toList()) , QuoteDto::setProducts);
////            mapper.map(quote -> quote.getItems()
////                    .stream()
////                    .map(quoteItemDtoConverter::convertToDto)
////                    .collect(Collectors.toList()), QuoteDto::setItems);
//        });
//
//        modelMapper.createTypeMap(QuoteDto.class, Quote.class).addMappings(mapper -> {
//            mapper.map(QuoteDto::getId, Quote::setId);
//            mapper.map(QuoteDto::getNumber, Quote::setNumber);
//            mapper.map(QuoteDto::getVersion, Quote::setVersion);
//            mapper.map(QuoteDto::getDescription, Quote::setDescription);
//            mapper.map(QuoteDto::getStatus, Quote::setStatus);
//            mapper.map(QuoteDto::getCreated, Quote::setCreated);
//            mapper.map(QuoteDto::getCost, Quote::setCost);
//            mapper.map(QuoteDto::getPrice, Quote::setPrice);
//            mapper.map(QuoteDto::getClientId, Quote::setClientId);
//            mapper.map(QuoteDto::getProducts, Quote::setProducts);
//            mapper.map(QuoteDto::getItems, Quote::setItems);
//            mapper.map(quote -> clientDtoConverter.convertToEntity(quote.getClientId()) , Quote::setClientId);
//            mapper.map(quote -> quote.getProducts()
//                    .stream()
//                    .map(stateBasedProductDtoConverter::convertToEntity)
//                    .collect(Collectors.toList()) , Quote::setProducts);
//            mapper.map(quote -> quote.getItems()
//                    .stream()
//                    .map(quoteItemDtoConverter::convertToEntity)
//                    .collect(Collectors.toList()), Quote::setItems);

//        });

        this.modelMapper = modelMapper;
    }


    public Quote convertToEntity(QuoteDto dto) {
        return modelMapper.map(dto, Quote.class);

    }

    public QuoteDto convertToDto(Quote entity) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String created = entity.getCreated().format(dateTimeFormatter);

        QuoteDto quoteDto = modelMapper.map(entity, QuoteDto.class);
        quoteDto.setCreated(created);
        return quoteDto;
    }

}
