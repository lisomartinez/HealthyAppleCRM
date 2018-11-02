package ar.com.healthyapple.crm_web.dto.Product;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class ProductProfileDto {
    private Long id;
    private String type;
    private String description;
    private List<ComponentProfileDto> components;

    public ProductProfileDto(String type, String description, List<ComponentProfileDto> components) {
        this.type = type;
        this.description = description;
        this.components = components;
    }
}
