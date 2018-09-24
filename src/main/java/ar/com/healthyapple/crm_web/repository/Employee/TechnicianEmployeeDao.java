package ar.com.healthyapple.crm_web.repository.Employee;

import ar.com.healthyapple.crm_web.model.Employee.TechnicianEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicianEmployeeDao extends JpaRepository<TechnicianEmployee, Long> {
}
