package ar.com.healthyapple.crm_web.repository.Product;

import ar.com.healthyapple.crm_web.model.Product.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;


@Repository
public interface ComponentRepository extends JpaRepository<Component, Long> {

    Optional<Component> findByDescriptionIgnoreCase(@NotEmpty @NotNull String name);
}
