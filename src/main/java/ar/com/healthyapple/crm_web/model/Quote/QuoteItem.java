package ar.com.healthyapple.crm_web.model.Quote;

import ar.com.healthyapple.crm_web.model.Product.Component;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public class QuoteItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST })
    private Component component;

    private BigDecimal cost;

    private BigDecimal price;

    public QuoteItem(String description, Component component, BigDecimal cost, BigDecimal price) {
        this.description = description;
        this.component = component;
        this.cost = cost;
        this.price = price;
    }
}
