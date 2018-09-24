package ar.com.healthyapple.crm_web.dto.Computer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class ProcessorDto extends ComponentDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer cores;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String socket;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer speed;

    public ProcessorDto() {
        super();
    }

    public ProcessorDto(Long id, String brand, String model, String partNumber, Integer cores, String socket, Integer speed) {
        super(id, brand, model, partNumber);
        this.cores = cores;
        this.socket = socket;
        this.speed = speed;
    }

    public ProcessorDto(String brand, String model, String partNumber, Integer cores, String socket, Integer speed) {
        super(brand, model, partNumber);
        this.cores = cores;
        this.socket = socket;
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "{\"id\":" + super.getId()
                + ",\"brand\":\"" + super.getBrand()
                + "\",\"model\":\"" + super.getModel()
                + "\",\"cores\":" + cores
                + ",\"socket\":\"" + socket
                + "\",\"speed\":" + speed
                + "\"}";
    }
}
