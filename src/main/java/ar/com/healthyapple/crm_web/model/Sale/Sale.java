package ar.com.healthyapple.crm_web.model.Sale;

import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Product.Product;
import ar.com.healthyapple.crm_web.model.Product.ProductState;
import ar.com.healthyapple.crm_web.model.Product.StateBasedProduct;
import ar.com.healthyapple.crm_web.model.Quote.Quote;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private SaleState state;

    private LocalDate startDate;

    @OneToOne
    private Quote quote;

    public Sale(String description, SaleState state, LocalDate startDate, Quote quote) {
        this.description = description;
        this.state = state;
        this.startDate = startDate;
        this.quote = quote;
    }
}
