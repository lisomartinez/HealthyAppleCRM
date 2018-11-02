package ar.com.healthyapple.crm_web.dto.Product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ={"id"})
public class ComponentDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ComponentTypeDto componentType;

    private List<SpecificationDto> specifications;

    public ComponentDto(String description, ComponentTypeDto componentType, List<SpecificationDto> specifications) {
        this.description = description;
        this.componentType = componentType;
        this.specifications = specifications;
    }
}
