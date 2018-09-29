package ar.com.healthyapple.crm_web.repository;

import ar.com.healthyapple.crm_web.model.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationRepository extends JpaRepository<Specification, Long> {
}
