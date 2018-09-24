package ar.com.healthyapple.crm_web.model.Computer;

import ar.com.healthyapple.crm_web.model.TechnicalSpecification;
import ar.com.healthyapple.crm_web.model.TechnicalSpecificationItem;
import ar.com.healthyapple.crm_web.model.TechnicalSpecificationType;

import javax.persistence.*;
import java.util.Arrays;

@Entity
public class MacComputer  extends TechnicalSpecification {

    public MacComputer(String name, TechnicalSpecificationType technicalSpecificationType, TechnicalSpecificationItem description, TechnicalSpecificationItem modelIdentifier, TechnicalSpecificationItem serialNumber, TechnicalSpecificationItem preInstalledMacOS, TechnicalSpecificationItem introductionDate, TechnicalSpecificationItem dislpaySize, TechnicalSpecificationItem memory, TechnicalSpecificationItem hardDrive, TechnicalSpecificationItem powerSupply, TechnicalSpecificationItem battery) {
        super(name, technicalSpecificationType, Arrays.asList(modelIdentifier, serialNumber, preInstalledMacOS, introductionDate, dislpaySize));
    }

    public MacComputer() {
        super();
    }
}
