package ar.com.healthyapple.crm_web.dto.Computer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Setter
@Getter
@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = HardDriveDto.class, name="hard-drive"),
        @JsonSubTypes.Type(value = MemoryDto.class, name="memory"),
        @JsonSubTypes.Type(value = MotherBoardDto.class, name="motherboard"),
        @JsonSubTypes.Type(value = PcCaseDto.class, name="pc-case"),
        @JsonSubTypes.Type(value = ProcessorDto.class, name="processor"),
        @JsonSubTypes.Type(value = VideoCardDto.class, name="video-card")
}
)
public abstract class ComponentDto {

    private Long id;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String brand;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String model;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String partNumber;

    public ComponentDto() {
    }

    public ComponentDto(Long id, String brand, String model, String partNumber) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.partNumber = partNumber;
    }

    public ComponentDto(String brand, String model, String partNumber) {
        this.brand = brand;
        this.model = model;
        this.partNumber = partNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComponentDto)) return false;
        ComponentDto that = (ComponentDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(partNumber, that.partNumber) &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, partNumber, brand, model);
    }
}
