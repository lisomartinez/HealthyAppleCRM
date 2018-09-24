package ar.com.healthyapple.crm_web.model.Computer;

import ar.com.healthyapple.crm_web.model.SpecificationItem;
import ar.com.healthyapple.crm_web.model.TechnicalSpecificationItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
public class VideoCard extends TechnicalSpecificationItem {

}
