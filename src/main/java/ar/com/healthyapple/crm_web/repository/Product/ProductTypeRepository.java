package ar.com.healthyapple.crm_web.repository.Product;

import ar.com.healthyapple.crm_web.model.Product.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long>, QuerydslPredicateExecutor<ProductType> {

    Optional<ProductType> findByNameIgnoreCase(@NotNull String name);
}
