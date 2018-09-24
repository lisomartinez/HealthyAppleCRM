package ar.com.healthyapple.crm_web.repository;

import ar.com.healthyapple.crm_web.model.TechnicalSpecification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicalSpecificationRespository extends JpaRepository<TechnicalSpecification, Long> {
}
