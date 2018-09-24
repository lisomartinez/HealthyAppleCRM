package ar.com.healthyapple.crm_web.dto.Computer;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
public class MemoryDto extends ComponentDto {


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String type;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer speed;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer size;

    public MemoryDto(Long id, String brand, String model, String partNumber, String type, Integer speed, Integer size) {
        super(id, brand, model, partNumber);
        this.type = type;
        this.speed = speed;
        this.size = size;
    }

    public MemoryDto(String brand, String model, String partNumber, String type, Integer speed, Integer size) {
        super(brand, model, partNumber);
        this.type = type;
        this.speed = speed;
        this.size = size;
    }

    public MemoryDto() {
        super();
    }



    @Override
    public String toString() {

        return "{\"id\":" + super.getId()
                + ",\"brand\":\"" + super.getBrand()
                + "\",\"model\":\"" + super.getModel()
                + "\",\"type\":\"" + type
                + "\",\"speed\":" + speed
                + "}";
    }
}
