package ar.com.healthyapple.crm_web.model.Computer;

import ar.com.healthyapple.crm_web.model.SpecificationItem;
import ar.com.healthyapple.crm_web.model.TechnicalSpecificationItem;
import ar.com.healthyapple.crm_web.model.TechnicalSpecificationItemType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
public class Processor extends TechnicalSpecificationItem {

    public Processor(String name, String description, TechnicalSpecificationItemType technicalSpecificationItemType, SpecificationItem cores, SpecificationItem socket, SpecificationItem speed) {
        super(name, description, technicalSpecificationItemType,  Arrays.asList(cores, socket, speed));
    }

    public Processor() {
        super();
    }

}
