package ar.com.healthyapple.crm_web.model.Quote;

import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Sale.ProductService;
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
    @Column(unique = true, name = "quote_status")
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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_service_id")
    private List<ProductService> productServices;

}
