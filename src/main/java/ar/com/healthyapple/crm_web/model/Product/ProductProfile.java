package ar.com.healthyapple.crm_web.model.Product;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ProductProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<ComponentProfile> components;

    public ProductProfile(String type, String description, List<ComponentProfile> components) {
        this.type = type;
        this.description = description;
        this.components = components;
    }

}
