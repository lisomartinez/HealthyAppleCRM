package ar.com.healthyapple.crm_web.model.Employee;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
public class SalesEmployee extends Employee{
    public SalesEmployee(@NotNull(message = "mobile cannot be null") Long mobile, @NotEmpty String fistName, @NotEmpty String lastName, @Email(message = "E-Mail should be valid") String email) {
        super(mobile, fistName, lastName, email);
    }

    public SalesEmployee() {
    }
}
