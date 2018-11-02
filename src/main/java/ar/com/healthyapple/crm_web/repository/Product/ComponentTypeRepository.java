package ar.com.healthyapple.crm_web.repository.Product;

import ar.com.healthyapple.crm_web.model.Product.ComponentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComponentTypeRepository extends JpaRepository<ComponentType, Long>, QuerydslPredicateExecutor<ComponentType> {
    Optional<ComponentType> findByNameIgnoreCase(String name);
}
