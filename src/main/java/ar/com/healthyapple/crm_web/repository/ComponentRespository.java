package ar.com.healthyapple.crm_web.repository;

import ar.com.healthyapple.crm_web.model.Component;
import ar.com.healthyapple.crm_web.model.ComponentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComponentRespository extends JpaRepository<Component, Long> {

}
