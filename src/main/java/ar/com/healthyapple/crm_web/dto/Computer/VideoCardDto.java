package ar.com.healthyapple.crm_web.dto.Computer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class VideoCardDto extends ComponentDto {

    public VideoCardDto() {
        super();
    }

    public VideoCardDto(Long id, String brand, String model, String partNumber) {
        super(id, brand, model, partNumber);
    }

    public VideoCardDto(String brand, String model, String partNumber) {
        super(brand, model, partNumber);
    }
}
