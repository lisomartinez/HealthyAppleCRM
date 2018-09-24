package ar.com.healthyapple.crm_web.repository;

import ar.com.healthyapple.crm_web.model.Product;
import ar.com.healthyapple.crm_web.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    Optional<Service> findByProductAndAndName(@NonNull Product product, @NotNull @NotEmpty String name);
}
