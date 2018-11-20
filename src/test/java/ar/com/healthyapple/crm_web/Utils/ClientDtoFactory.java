package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.dto.Client.ClientDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ClientDtoFactory {
    public static ClientDto makeEmptyClientDto() {
        return new ClientDto();
    }

    public static ClientDto makeClientDto() {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);
        clientDto.setMobile(1112121212L);
        clientDto.setStartDate("2010-03-11");
        clientDto.setFirstName("firstName");
        clientDto.setLastName("lastName");
        clientDto.setEmail("clientEmail@gmail.com");
        clientDto.setAddress("address");
        clientDto.setProducts(new ArrayList<>(Arrays.asList(ProductDtoFactory.makeProductDto())));
        return clientDto;
    }
}
