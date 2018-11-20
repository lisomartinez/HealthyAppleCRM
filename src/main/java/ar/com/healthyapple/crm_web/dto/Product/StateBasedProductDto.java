package ar.com.healthyapple.crm_web.dto.Product;

import ar.com.healthyapple.crm_web.dto.quote.QuoteItemDto;
import ar.com.healthyapple.crm_web.model.Product.ProductState;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ={"id"})
@ToString
public class StateBasedProductDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProductState state;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal cost;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal price;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProductDto product;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<QuoteItemDto> items;

    public StateBasedProductDto(ProductState state, BigDecimal cost, BigDecimal price, ProductDto product, List<QuoteItemDto> items) {
        this.state = state;
        this.cost = cost;
        this.price = price;
        this.product = product;
        this.items = items;
    }
}
