package ar.com.healthyapple.crm_web.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Setter
@Getter
@ToString
public class SpecificationItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String specification;

    public SpecificationItem() {
    }

    public SpecificationItem(String name, String specification) {
        this.name = name;
        this.specification = specification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpecificationItem)) return false;
        SpecificationItem that = (SpecificationItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
