package ar.com.healthyapple.crm_web.dto.Computer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class PowerSupplyDto extends ComponentDto {


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer power;

    public PowerSupplyDto(Long id, String brand, String model, String partNumber, Integer power) {
        super(id, brand, model, partNumber);
        this.power = power;
    }

    public PowerSupplyDto() {
        super();
    }

    public PowerSupplyDto(String brand, String model, String partNumber, Integer power) {
        super(brand, model, partNumber);
        this.power = power;
    }

    @Override
    public String toString() {
        return "{\"id\":" + super.getId()
                + ",\"brand\":\"" + super.getBrand()
                + "\",\"model\":\"" + super.getModel()
                + "\",\"power\":" + power
                + "\"}";
    }
}