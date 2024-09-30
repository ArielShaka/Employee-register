package register.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {

    private String firstName;
    private String lastName;
    private String email;

    public Employee(String email , String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
