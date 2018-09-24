package ar.com.healthyapple.crm_web.dto.Computer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
public class HardDriveDto extends ComponentDto{

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String type;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String size;

    public HardDriveDto() {
        super();
    }

    public HardDriveDto(Long id, String brand, String model, String partNumber, String type, String size) {
        super(id, brand, model, partNumber);
        this.type = type;
        this.size = size;
    }

    public HardDriveDto(String brand, String model, String partNumber, String type, String size) {
        super(brand, model, partNumber);
        this.type = type;
        this.size = size;
    }

    @Override
    public String toString() {

        return "{\"id\":" + super.getId()
                + ",\"partNumber\":\"" + super.getPartNumber()
                + ",\"brand\":\"" + super.getBrand()
                + "\",\"model\":\"" + super.getModel()
                + "\",\"type\":\"" + type
                + "\",\"size\":\"" + size + "\"}";
    }
}
