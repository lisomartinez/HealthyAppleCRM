package ar.com.healthyapple.crm_web.model.Product;

import lombok.*;

import javax.persistence.*;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ComponentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String description;

    private Boolean multiple;

    @ElementCollection
    private Map<String, String> specifications;

    public ComponentProfile(String type, String description, Boolean multiple, Map<String, String> specifications) {
        this.type = type;
        this.description = description;
        this.multiple = multiple;
        this.specifications = specifications;
    }
}
