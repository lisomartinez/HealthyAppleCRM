package ar.com.healthyapple.crm_web.model;

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
public class TechnicalSpecification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(cascade = CascadeType.PERSIST)
    private TechnicalSpecificationType technicalSpecificationType;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Component> components;

    public TechnicalSpecification(TechnicalSpecificationType technicalSpecificationType, List<Component> components) {
        this.technicalSpecificationType = technicalSpecificationType;
        this.components = components;
    }

    public TechnicalSpecification() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TechnicalSpecification)) return false;
        TechnicalSpecification that = (TechnicalSpecification) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
