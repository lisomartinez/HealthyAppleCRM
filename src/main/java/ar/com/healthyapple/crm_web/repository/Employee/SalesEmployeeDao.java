package ar.com.healthyapple.crm_web.repository.Employee;

import ar.com.healthyapple.crm_web.model.Employee.SalesEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesEmployeeDao extends JpaRepository<SalesEmployee, Long> {

}
