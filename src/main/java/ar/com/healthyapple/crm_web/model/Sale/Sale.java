package ar.com.healthyapple.crm_web.model.Sale;

import ar.com.healthyapple.crm_web.model.Client.Client;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    private String tag;

    private String description;

    @Enumerated(EnumType.STRING)
    private SaleState state;

    //TODO: orphanRemoval = true,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    private LocalDate originDate;

    private LocalDate dueDate;

    private BigDecimal price;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductService> productServiceList;


}
