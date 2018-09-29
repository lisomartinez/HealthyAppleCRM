package ar.com.healthyapple.crm_web.dto.Computer;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ={"id"})
public class TechnicalSpecificationTypeDto {

    private Long id;

    private String name;
}
