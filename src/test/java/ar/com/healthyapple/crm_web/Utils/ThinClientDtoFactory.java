package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.dto.Client.ThinClientDto;

import java.time.LocalDate;

public class ThinClientDtoFactory {
    public static ThinClientDto makeThinClientDto() {
        ThinClientDto thinClientDto = new ThinClientDto();
        thinClientDto.setId(1L);
        thinClientDto.setMobile(111212121L);
        thinClientDto.setFirstName("firstName");
        thinClientDto.setLastName("lastName");
        thinClientDto.setEmail("test@gmail.com");
        thinClientDto.setAddress("address");
        thinClientDto.setStartDate(LocalDate.of(2010, 8, 11));
        return thinClientDto;
    }
}
