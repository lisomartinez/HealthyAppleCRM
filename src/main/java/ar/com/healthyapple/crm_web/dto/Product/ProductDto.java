package ar.com.healthyapple.crm_web.dto.Product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ={"id"})
@ToString
public class ProductDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ComponentDto> components;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProductTypeDto productType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProductProfileDto profile;

    public ProductDto(String description, List<ComponentDto> components, ProductTypeDto productType, ProductProfileDto profile) {
        this.description = description;
        this.components = components;
        this.productType = productType;
        this.profile = profile;
    }
}
