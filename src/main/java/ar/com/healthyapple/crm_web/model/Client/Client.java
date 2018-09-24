package ar.com.healthyapple.crm_web.model.Client;

import ar.com.healthyapple.crm_web.model.Product;
import ar.com.healthyapple.crm_web.model.Sale.Sale;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ={"mobile"})
@ToString
public class Client {

    @Id
    private Long mobile;

    private String firstName;

    private String lastName;

    private String email;

    @Embedded
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Product> products;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "client")
    List<Sale> services;

}
