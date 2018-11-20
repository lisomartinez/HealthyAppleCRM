package ar.com.healthyapple.crm_web.repository.Sale;

import ar.com.healthyapple.crm_web.model.Sale.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
