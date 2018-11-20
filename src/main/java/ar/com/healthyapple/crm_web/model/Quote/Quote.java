package ar.com.healthyapple.crm_web.model.Quote;

import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Product.StateBasedProduct;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
@Table(name = "quotes")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quote_id")
    private Long id;

    @Column(name = "quote_number")
    private Long number;

    @Column(name = "quote_version")
    private Integer version;

    @Column(name = "quote_description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "quote_status")
    private QuoteState status;

    @Column(name = "quote_created_date")
    private LocalDateTime created;

    @Column(name = "quote_cost")
    private BigDecimal cost;

    @Column(name = "quote_price")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_client_id")
    private Client client;


//    @ElementCollection(fetch = FetchType.LAZY)
//    @CollectionTable(name = "FTT_REGISTRI_ESCLUSIONI", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "FTT_FK_ESCLUSIONE_TO_REGISTRO"), joinColumns = @JoinColumn(name = "REGISTRO_ID"))
//    @MapKeyColumn(name = "CLAUSOLA_ESCLUSIONE", length = 40, nullable = false)
//    @MapKeyClass(FttEsclusioneType.class)
//    @MapKeyEnumerated(EnumType.STRING)
//    @Column(name = "RECORD_COUNT", nullable = false)

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "quote_products" )
    private List<StateBasedProduct> products;

    public Quote(Long number, Integer version, String description, QuoteState status, LocalDateTime created, BigDecimal cost, BigDecimal price, Client client, List<StateBasedProduct> products, List<QuoteItem> items) {
        this.number = number;
        this.version = version;
        this.description = description;
        this.status = status;
        this.created = created;
        this.cost = cost;
        this.price = price;
        this.client = client;
        this.products = products;
    }
}
