package ar.com.healthyapple.crm_web.repository.Product;

import ar.com.healthyapple.crm_web.model.Product.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationRepository extends JpaRepository<Specification, Long>, QuerydslPredicateExecutor<Specification> {
}
