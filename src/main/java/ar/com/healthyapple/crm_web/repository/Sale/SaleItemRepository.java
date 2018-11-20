package ar.com.healthyapple.crm_web.repository.Sale;

import ar.com.healthyapple.crm_web.model.Product.Product;
import ar.com.healthyapple.crm_web.model.Sale.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {

//    Optional<SaleItem> findByProductAndAndName(@NonNull Product product, @NotEmpty String name);
}
