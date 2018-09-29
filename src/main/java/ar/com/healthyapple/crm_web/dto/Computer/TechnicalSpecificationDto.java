package ar.com.healthyapple.crm_web.dto.Computer;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class TechnicalSpecificationDto {

    private Long id;

    private TechnicalSpecificationTypeDto technicalSpecificationType;

    private List<ComponentDto> components;

}
