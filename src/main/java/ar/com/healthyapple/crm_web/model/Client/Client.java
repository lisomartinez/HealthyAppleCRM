package ar.com.healthyapple.crm_web.model.Client;

import ar.com.healthyapple.crm_web.model.Product;
import ar.com.healthyapple.crm_web.model.Sale.Sale;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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

    private LocalDate startDate;

    private String firstName;

    private String lastName;

    private String email;

    private  String address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Product> products;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "client")
    List<Sale> services;

    public Client(Long mobile, LocalDate startDate, String firstName, String lastName, String email, String address) {
        this.mobile = mobile;
        this.startDate = startDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }
}
