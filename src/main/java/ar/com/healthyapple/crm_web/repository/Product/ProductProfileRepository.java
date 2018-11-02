package ar.com.healthyapple.crm_web.repository.Product;

import ar.com.healthyapple.crm_web.model.Product.ProductProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductProfileRepository extends JpaRepository<ProductProfile, Long>, QuerydslPredicateExecutor<ProductProfile>, ProductProfileCustomRepository {

    Optional<ProductProfile> findByTypeIgnoreCase(String name);
}
