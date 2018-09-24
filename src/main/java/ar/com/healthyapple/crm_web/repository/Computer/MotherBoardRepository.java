package ar.com.healthyapple.crm_web.repository.Computer;

import ar.com.healthyapple.crm_web.model.Computer.MotherBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MotherBoardRepository extends JpaRepository<MotherBoard, Long> {
   Optional<MotherBoard> findByBrandAndModelAllIgnoreCase(String brand, String model);
   Optional<MotherBoard> findByPartNumberIgnoreCase(String partNumber);
}
