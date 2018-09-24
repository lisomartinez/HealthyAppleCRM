package ar.com.healthyapple.crm_web.repository;

import ar.com.healthyapple.crm_web.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
