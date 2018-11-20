package ar.com.healthyapple.crm_web.dto.Product;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ={"id"})
@ToString
public class ProductWithoutProfileDto {


    private Long id;

    private String description;

    private List<ComponentDto> components;

    private ProductTypeDto productType;
}
