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
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class TechnicalSpecification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    private TechnicalSpecificationType technicalSpecificationType;

    @OneToMany
    private List<TechnicalSpecificationItem> technicalSpecificationItems;

    public TechnicalSpecification(String name, TechnicalSpecificationType technicalSpecificationType, List<TechnicalSpecificationItem> technicalSpecificationItems) {
        this.name = name;
        this.technicalSpecificationType = technicalSpecificationType;
        this.technicalSpecificationItems = technicalSpecificationItems;
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
