package ar.com.healthyapple.crm_web.dto.Product;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ={"id"})
@ToString
public class SpecificationDto {

    private Long id;
    private String name;
    private String description;
}
