package ar.com.healthyapple.crm_web.repository.Product;

import ar.com.healthyapple.crm_web.model.Product.ComponentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface ComponentProfileRepository extends JpaRepository<ComponentProfile, Long>, QuerydslPredicateExecutor<ComponentProfile>, ComponentProfileCustomRepository {

    Optional<ComponentProfile> findByTypeIgnoreCase(@NotNull @NotEmpty String name);
}
