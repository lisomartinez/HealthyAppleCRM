package ar.com.healthyapple.crm_web.repository.Service;

import ar.com.healthyapple.crm_web.model.Product.Product;
import ar.com.healthyapple.crm_web.model.Sale.ProductService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<ProductService, Long> {

    Optional<ProductService> findByProductAndAndName(@NonNull Product product, @NotEmpty String name);
}
