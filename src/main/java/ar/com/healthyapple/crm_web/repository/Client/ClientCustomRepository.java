package ar.com.healthyapple.crm_web.repository.Client;

import ar.com.healthyapple.crm_web.model.Client.Client;
import ar.com.healthyapple.crm_web.model.Product.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ClientCustomRepository {

    Map<Long, String> findClientProductsProfileDescriptionByProductType(Long clientId);

}
