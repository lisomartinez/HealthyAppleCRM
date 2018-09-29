package ar.com.healthyapple.crm_web.dto.Computer;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ={"id"})
public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private TechnicalSpecificationDto specifications;

}
