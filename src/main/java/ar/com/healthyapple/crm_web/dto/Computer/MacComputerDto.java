package ar.com.healthyapple.crm_web.dto.Computer;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString(callSuper = true)
public class MacComputerDto extends ComputerDto {


    private String modelIdentifier;

    private String serialNumber;

    private LocalDate introductionDate;

    private String preInstalledMacOS;

    private Double displaySize;

    public MacComputerDto() {
        super(ComputerType.MAC);
    }

    public MacComputerDto(String modelIdentifier, String serialNumber, LocalDate introductionDate, String preInstalledMacOS, Double displaySize) {
        super(ComputerType.MAC);
        this.modelIdentifier = modelIdentifier;
        this.serialNumber = serialNumber;
        this.introductionDate = introductionDate;
        this.preInstalledMacOS = preInstalledMacOS;
        this.displaySize = displaySize;
    }

    public MacComputerDto(Long id, MotherBoardDto motherBoard, ProcessorDto processor, MemoryDto memory, HardDriveDto hardDrive, String modelIdentifier, String serialNumber, LocalDate introductionDate, String preInstalledMacOS, Double displaySize) {
        super(id, ComputerType.MAC, motherBoard, processor, memory, hardDrive);
        this.modelIdentifier = modelIdentifier;
        this.serialNumber = serialNumber;
        this.introductionDate = introductionDate;
        this.preInstalledMacOS = preInstalledMacOS;
        this.displaySize = displaySize;
    }

    public MacComputerDto( MotherBoardDto motherBoard, ProcessorDto processor, MemoryDto memory, HardDriveDto hardDrive, String modelIdentifier, String serialNumber, LocalDate introductionDate, String preInstalledMacOS, Double displaySize) {
        super(ComputerType.MAC, motherBoard, processor, memory, hardDrive);
        this.modelIdentifier = modelIdentifier;
        this.serialNumber = serialNumber;
        this.introductionDate = introductionDate;
        this.preInstalledMacOS = preInstalledMacOS;
        this.displaySize = displaySize;
    }


    //    @Override
//    public String toString() {
//        return "{\"id\":" + super.getId()
//                + ",\"computerType\":\"" + super.getComputerType()
//                + ", modelIdentifier\":\"" + modelIdentifier
//                + "\",\"serialNumber\":\"" + serialNumber
//                + "\",\"introductionDate\":\"" + introductionDate
//                + "\",\"preInstalledMacOS\":\"" + preInstalledMacOS
//                + "\",\"displaySize\":" + displaySize
//                + ",\"motherboard\":\"" + super.getMotherBoard()
//                + "\",\"processor\":\"" + super.getProcessor()
//                + "\",\"memory\":" + super.getMemory()
//                + "\",\"hard-drive\":" + super.getHardDrive()
//                + "}";
//    }
}
