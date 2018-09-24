package ar.com.healthyapple.crm_web.repository.Computer;

import ar.com.healthyapple.crm_web.model.Computer.HardDrive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface HardDriveRepository extends JpaRepository<HardDrive, Long> {

    Optional<HardDrive> findHardDriveByBrandAndModelAllIgnoreCase(String brand, String model);

    Optional<HardDrive> findByPartNumberIgnoreCase(String partNumber);
    }
