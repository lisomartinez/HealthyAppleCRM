package ar.com.healthyapple.crm_web.model.Computer;

import ar.com.healthyapple.crm_web.model.Specification;
import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpansionSlot {

    private Specification type;

    private Specification speed;


}
