package ar.com.healthyapple.crm_web.dto.Computer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class PcCaseDto extends ComponentDto {



    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String type;

    public PcCaseDto() {
        super();
    }

    public PcCaseDto(Long id, String brand, String model, String partNumber, String type) {
        super(id, brand, model, partNumber);
        this.type = type;
    }

    public PcCaseDto(String brand, String model, String partNumber, String type) {
        super(brand, model, partNumber);
        this.type = type;
    }


    //    @Override
//    public String toString() {
//        return "{\"id\":" + id
//                + ",\"brand\":\"" + brand
//                + "\",\"model\":\"" + model
//                + ",\"type\":\"" + type
//                + "\"}";
//    }
}
