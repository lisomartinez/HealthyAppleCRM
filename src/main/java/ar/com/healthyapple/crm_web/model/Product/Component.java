package ar.com.healthyapple.crm_web.model.Product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Setter
@Getter
@ToString
public class Component {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne( fetch = FetchType.EAGER)
    private ComponentType componentType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Specification> specifications;

    public Component() {
    }

    public Component(String description, ComponentType componentType, List<Specification> specifications) {
        this.description = description;
        this.componentType = componentType;
        this.specifications = specifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Component)) return false;
        Component that = (Component) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
