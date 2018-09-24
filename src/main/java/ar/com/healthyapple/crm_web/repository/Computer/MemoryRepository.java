package ar.com.healthyapple.crm_web.repository.Computer;

import ar.com.healthyapple.crm_web.model.Computer.HardDrive;
import ar.com.healthyapple.crm_web.model.Computer.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemoryRepository extends JpaRepository<Memory, Long> {

    Optional<Memory> findMemoryByBrandAndModelAllIgnoreCase(@NonNull String brand, @NonNull String model);

    Optional<Memory> findByPartNumberIgnoreCase(String partNumber);

}
