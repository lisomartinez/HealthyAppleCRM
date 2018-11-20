package ar.com.healthyapple.crm_web.dto.Product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ={"id"})
@ToString
public class ComponentProfileDto {

    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String type;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean multiple;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> specifications;

    public ComponentProfileDto(String type, String description, Boolean multiple, Map<String, String> specifications) {
        this.type = type;
        this.description = description;
        this.multiple = multiple;
        this.specifications = specifications;
    }
}
