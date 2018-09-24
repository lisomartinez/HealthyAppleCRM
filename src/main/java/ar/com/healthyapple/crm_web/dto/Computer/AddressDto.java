package ar.com.healthyapple.crm_web.dto.Computer;

import lombok.Data;

@Data
public class AddressDto {

    private String city;

    private String postalCode;

    private String street;

    private String floor;

    private String apartament;
}
