package ar.com.healthyapple.crm_web.model.Product;


import ar.com.healthyapple.crm_web.model.Quote.QuoteItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
@Table(name = "state_based_products")
public class StateBasedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_based_products_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "state_based_products_state")
    private ProductState state;

    @Column(name = "state_based_products_cost")
    private BigDecimal cost;

    @Column(name = "state_based_products_price")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "state_based_products_product")
    private Product product;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "state_based_products_state_quote_items")
    private List<QuoteItem> items;

    public StateBasedProduct(ProductState state, BigDecimal cost, BigDecimal price, Product product, List<QuoteItem> items) {
        this.state = state;
        this.cost = cost;
        this.price = price;
        this.product = product;
        this.items = items;
    }
}
