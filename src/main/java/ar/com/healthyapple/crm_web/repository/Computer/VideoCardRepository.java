package ar.com.healthyapple.crm_web.repository.Computer;

import ar.com.healthyapple.crm_web.model.Computer.VideoCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VideoCardRepository extends JpaRepository<VideoCard, Long> {
}
