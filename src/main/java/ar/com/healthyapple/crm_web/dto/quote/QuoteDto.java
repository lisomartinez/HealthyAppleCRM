package ar.com.healthyapple.crm_web.dto.quote;

import ar.com.healthyapple.crm_web.dto.Client.ThinClientDto;
import ar.com.healthyapple.crm_web.dto.Product.StateBasedProductDto;
import ar.com.healthyapple.crm_web.model.Quote.QuoteState;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
public class QuoteDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long number;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer version;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private QuoteState status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private String created;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal cost;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal price;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long clientId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<StateBasedProductDto> products;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> availableOperations;

    public QuoteDto(Long number, Integer version, String description, QuoteState status, String created, BigDecimal cost, BigDecimal price, Long clientId, List<StateBasedProductDto> products, List<String> availableOperations) {
        this.number = number;
        this.version = version;
        this.description = description;
        this.status = status;
        this.created = created;
        this.cost = cost;
        this.price = price;
        this.clientId = clientId;
        this.products = products;
        this.availableOperations = availableOperations;
    }
}
