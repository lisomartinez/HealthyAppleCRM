package ar.com.healthyapple.crm_web.model.Sale;

import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Service;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tag;

    private String description;

    private SaleStateEnum state;

    //TODO: orphanRemoval = true,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    private LocalDate originDate;

    private LocalDate dueDate;

    private BigDecimal totalCost;

    private BigDecimal finalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Service> serviceList;

    public Sale(String tag, String description, SaleStateEnum state, Client client, LocalDate originDate, LocalDate dueDate, BigDecimal totalCost, BigDecimal finalPrice, List<Service> serviceList) {
        this.tag = tag;
        this.description = description;
        this.state = state;
        this.client = client;
        this.originDate = originDate;
        this.dueDate = dueDate;
        this.totalCost = totalCost;
        this.finalPrice = finalPrice;
        this.serviceList = serviceList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sale)) return false;
        Sale sale = (Sale) o;
        return Objects.equals(id, sale.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
