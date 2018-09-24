package ar.com.healthyapple.crm_web.repository;

import ar.com.healthyapple.crm_web.model.Sale.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
