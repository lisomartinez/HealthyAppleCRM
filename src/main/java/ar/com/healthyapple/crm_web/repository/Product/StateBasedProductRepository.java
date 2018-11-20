package ar.com.healthyapple.crm_web.repository.Product;

import ar.com.healthyapple.crm_web.model.Product.StateBasedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateBasedProductRepository extends JpaRepository<StateBasedProduct, Long> {
}
