package ar.com.healthyapple.crm_web.model.Sale;

import ar.com.healthyapple.crm_web.model.Product.Component;
import ar.com.healthyapple.crm_web.model.Product.Product;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    private Component component;

    private BigDecimal cost;

    private BigDecimal price;

    public SaleItem(String description, Component component, BigDecimal cost, BigDecimal price) {
        this.description = description;
        this.component = component;
        this.cost = cost;
        this.price = price;
    }
}
