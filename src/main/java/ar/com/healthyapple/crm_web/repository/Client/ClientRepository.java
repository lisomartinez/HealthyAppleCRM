package ar.com.healthyapple.crm_web.repository.Client;

import ar.com.healthyapple.crm_web.model.Client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
