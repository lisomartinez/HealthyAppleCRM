package ar.com.healthyapple.crm_web.dto.Computer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class MotherBoardDto extends ComponentDto {


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String socket;

    public MotherBoardDto() {
        super();
    }

    public MotherBoardDto(Long id, String brand, String model, String partNumber, String socket) {
        super(id, brand, model, partNumber);
        this.socket = socket;
    }

    public MotherBoardDto(String brand, String model, String partNumber, String socket) {
        super(brand, model, partNumber);
        this.socket = socket;
    }

    @Override
    public String toString() {
        return "{\"id\":" + super.getId()
                + ",\"brand\":\"" + super.getBrand()
                + "\",\"model\":\"" + super.getModel()
                + ",\"socket\":\"" + socket
                + "\"}";
    }
}
