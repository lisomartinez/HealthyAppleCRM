package ar.com.healthyapple.crm_web.Utils;

import ar.com.healthyapple.crm_web.model.Product.ComponentProfile;
import ar.com.healthyapple.crm_web.model.Product.ProductProfile;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductProfileFactory {
    public static ProductProfile makeProductProfile() {
        ProductProfile productProfile = new ProductProfile();
        productProfile.setId(1L);
        productProfile.setDescription("description");
        productProfile.setType("type");
        productProfile.setComponents(new ArrayList<>(Arrays.asList(ComponentProfileFactory.makeComponentProfile())));
        return productProfile;
    }
}
