package ar.com.healthyapple.crm_web.repository.Computer;

import ar.com.healthyapple.crm_web.model.Computer.MacComputer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MacComputerRepository extends JpaRepository<MacComputer, Long> {
}
