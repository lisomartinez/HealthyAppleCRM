package ar.com.healthyapple.crm_web.model.Sale;

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
public class ProductService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToOne
    private Product product;

    private BigDecimal cost;

    public ProductService(String name, String description, Product product, BigDecimal cost) {
        this.name = name;
        this.description = description;
        this.product = product;
        this.cost = cost;
    }
}
