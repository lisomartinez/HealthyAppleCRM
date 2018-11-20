package ar.com.healthyapple.crm_web.dto.quote;

import ar.com.healthyapple.crm_web.dto.Product.ComponentDto;
import ar.com.healthyapple.crm_web.model.Product.Component;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public class QuoteItemDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ComponentDto component;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal cost;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal price;

    public QuoteItemDto(String description, ComponentDto component, BigDecimal cost, BigDecimal price) {
        this.description = description;
        this.component = component;
        this.cost = cost;
        this.price = price;
    }
}
