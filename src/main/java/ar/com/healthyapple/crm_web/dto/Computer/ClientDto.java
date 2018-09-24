package ar.com.healthyapple.crm_web.dto.Computer;

import ar.com.healthyapple.crm_web.dto.service.ServiceDto;
import com.fasterxml.jackson.annotation.JsonInclude.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ={"mobile"})
public class ClientDto {

    @JsonInclude(Include.NON_NULL)
    private Long mobile;

    private String firstName;

    private String lastName;

    private AddressDto address;

    private String email;

    private List<ComputerDto> computerList;

    private List<ServiceDto> serviceList;



}
