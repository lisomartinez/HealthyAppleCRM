package ar.com.healthyapple.crm_web.repository;

import ar.com.healthyapple.crm_web.model.TechnicalSpecificationItem;
import ar.com.healthyapple.crm_web.model.TechnicalSpecificationItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@Repository
public interface TechnicalSpecificationItemRepository extends JpaRepository<TechnicalSpecificationItem, Long> {


    Optional<TechnicalSpecificationItem> findByNameAndDescriptionAndTechnicalSpecificationItemType (@NonNull @NotEmpty String name, @NonNull @NotEmpty String description, @NonNull TechnicalSpecificationItemType technicalSpecificationItemType);
}
