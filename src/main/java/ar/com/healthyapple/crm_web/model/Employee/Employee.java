package ar.com.healthyapple.crm_web.model.Employee;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

@Getter
@Setter
@ToString
public abstract class Employee {

    @Id
    @NotNull(message = "mobile cannot be null")
    private Long mobile;

    @NotEmpty
    private String fistName;

    @NotEmpty
    private String lastName;

    @Email(message = "E-Mail should be valid")
    private String email;

    public Employee(@NotNull(message = "mobile cannot be null") Long mobile, @NotEmpty String fistName, @NotEmpty String lastName, @Email(message = "E-Mail should be valid") String email) {
        this.mobile = mobile;
        this.fistName = fistName;
        this.lastName = lastName;
        this.email = email;
    }

    public Employee() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(mobile, employee.mobile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mobile);
    }
}
