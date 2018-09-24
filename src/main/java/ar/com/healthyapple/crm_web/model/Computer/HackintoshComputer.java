package ar.com.healthyapple.crm_web.model.Computer;

import ar.com.healthyapple.crm_web.model.TechnicalSpecification;
import ar.com.healthyapple.crm_web.model.TechnicalSpecificationType;

import javax.persistence.*;
import java.util.Arrays;

@Entity
public class HackintoshComputer extends TechnicalSpecification {


    public HackintoshComputer() {
        super();
    }

    public HackintoshComputer(String name, TechnicalSpecificationType technicalSpecificationType, MotherBoard motherBoard, Processor processor, Memory memory, HardDrive hardDrive, PcCase computerPcCase, PowerSupply powerSupply, VideoCard videoCard) {
        super(name, technicalSpecificationType, Arrays.asList(motherBoard, processor, memory, hardDrive, computerPcCase, powerSupply, videoCard));
    }
}
