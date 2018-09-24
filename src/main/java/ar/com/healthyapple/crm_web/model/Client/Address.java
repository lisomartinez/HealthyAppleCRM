package ar.com.healthyapple.crm_web.model.Client;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
public class Address {

    private String city;

    private String postalCode;

    private String street;

    private String floor;

    private String apartament;
}
