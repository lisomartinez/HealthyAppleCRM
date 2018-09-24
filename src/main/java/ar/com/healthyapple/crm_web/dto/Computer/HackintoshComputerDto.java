package ar.com.healthyapple.crm_web.dto.Computer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString(callSuper = true)
public class HackintoshComputerDto extends ComputerDto {

    private ComputerType computerType;

    private PcCaseDto computerCase;

    private PowerSupplyDto powerSupply;

    private VideoCardDto videoCard;

    public HackintoshComputerDto() {
        super(ComputerType.HACKINTOSH);
    }



    public HackintoshComputerDto(MotherBoardDto motherBoard, ProcessorDto processor, MemoryDto memory, HardDriveDto hardDrive, PcCaseDto computerCase, PowerSupplyDto powerSupply, VideoCardDto videoCard) {
        super(ComputerType.HACKINTOSH, motherBoard, processor, memory, hardDrive);
        this.computerType = ComputerType.HACKINTOSH;
        this.computerCase = computerCase;
        this.powerSupply = powerSupply;
        this.videoCard = videoCard;
    }

    public HackintoshComputerDto(Long id, MotherBoardDto motherBoard, ProcessorDto processor, MemoryDto memory, HardDriveDto hardDrive, PcCaseDto computerCase, PowerSupplyDto powerSupply, VideoCardDto videoCard) {
        super(id, ComputerType.HACKINTOSH, motherBoard, processor, memory, hardDrive);
        this.computerType = ComputerType.HACKINTOSH;
        this.computerCase = computerCase;
        this.powerSupply = powerSupply;
        this.videoCard = videoCard;
    }
}
