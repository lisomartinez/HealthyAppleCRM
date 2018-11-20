package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.model.Client.Client;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ClientFactory {

    public static Client makeClient() {
        Client client = new Client();
        client.setId(1L);
        client.setMobile(1112121212L);
        client.setStartDate(LocalDate.of(2010, 03, 11));
        client.setFirstName("firstName");
        client.setLastName("lastName");
        client.setEmail("clientEmail@gmail.com");
        client.setAddress("address");
        client.setProducts(new ArrayList<>(Arrays.asList(ProductFactory.makeProduct())));

        return client;
    }

    public static Client makeEmptyClient() {
        return new Client();
    }
}
