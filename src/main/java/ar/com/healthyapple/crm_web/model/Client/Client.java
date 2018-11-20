package ar.com.healthyapple.crm_web.model.Client;

import ar.com.healthyapple.crm_web.model.Product.Product;
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
@EqualsAndHashCode(of = {"id"})
@ToString
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "mobile")
    private Long mobile;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private  String address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<Product> products;

    public Client(Long mobile, LocalDate startDate, String firstName, String lastName, String email, String address) {
        this.mobile = mobile;
        this.startDate = startDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    public Client(Long mobile, LocalDate startDate, String firstName, String lastName, String email, String address, List<Product> products) {
        this.mobile = mobile;
        this.startDate = startDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.products = products;
    }
}
