package ar.com.healthyapple.crm_web.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Getter
@ToString
public abstract class TechnicalSpecificationItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToOne
    private TechnicalSpecificationItemType technicalSpecificationItemType;

    @OneToMany
    private List<SpecificationItem> specificationItems;

    public TechnicalSpecificationItem() {
    }

    public TechnicalSpecificationItem(String name, String description, TechnicalSpecificationItemType technicalSpecificationItemType, List<SpecificationItem> specificationItems) {
        this.name = name;
        this.description = description;
        this.technicalSpecificationItemType = technicalSpecificationItemType;
        this.specificationItems = specificationItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TechnicalSpecificationItem)) return false;
        TechnicalSpecificationItem that = (TechnicalSpecificationItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
