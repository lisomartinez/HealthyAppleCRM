package ar.com.healthyapple.crm_web.controller.DtoConverter;

import ar.com.healthyapple.crm_web.dto.Product.SpecificationDto;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
        ClientDtoConverterTest.class,
        ComponentDtoConverterTest.class,
        ComponentTypeDtoConverterTest.class,
        ProductDtoConverterTest.class,
        ProductProfileDtoConverterTest.class,
        ProductTypeDtoConverterTest.class,
        QuoteDtoConverterTest.class,
        RequestQuoteDtoConverterTest.class,
        ThinClientDtoConverterTest.class,
        QuoteItemDtoConverterTest.class,
        SaleItemDtoConverterTest.class,
        SpecificationDtoConverterTest.class,
        StateBasedProductDtoConverterTest.class
})
public class AllDtoConverterIT {
}
