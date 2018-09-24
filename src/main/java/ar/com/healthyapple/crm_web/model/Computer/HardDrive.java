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
public class HardDrive extends TechnicalSpecificationItem {

    public HardDrive(String name, String description, TechnicalSpecificationItemType technicalSpecificationItemType, SpecificationItem type, SpecificationItem size) {
        super(name,description, technicalSpecificationItemType, Arrays.asList(type, size));
    }

    public HardDrive() {
        super();
    }


}
