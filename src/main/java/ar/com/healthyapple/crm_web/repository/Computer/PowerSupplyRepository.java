package ar.com.healthyapple.crm_web.repository.Computer;

import ar.com.healthyapple.crm_web.model.Computer.PowerSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PowerSupplyRepository extends JpaRepository<PowerSupply, Long> {

    Optional<PowerSupply> findByBrandAndModelAllIgnoreCase(String brand, String model);

    Optional<PowerSupply> findByPartNumberIgnoreCase(String partNumber);
}
