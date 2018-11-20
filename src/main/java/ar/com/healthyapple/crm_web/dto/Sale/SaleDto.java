package ar.com.healthyapple.crm_web.dto.Sale;

import ar.com.healthyapple.crm_web.dto.Client.ClientDto;
import ar.com.healthyapple.crm_web.dto.Client.ThinClientDto;
import ar.com.healthyapple.crm_web.dto.Product.ProductDto;
import ar.com.healthyapple.crm_web.dto.Product.StateBasedProductDto;
import ar.com.healthyapple.crm_web.dto.quote.QuoteDto;
import ar.com.healthyapple.crm_web.model.Product.ProductState;
import ar.com.healthyapple.crm_web.model.Sale.SaleState;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ={"id"})
public class SaleDto {

    private Long id;

    private String description;

    private SaleState state;

    private String startDate;

    private QuoteDto quote;
}
