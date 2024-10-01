package register.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import register.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class TestDataUtil {

    public static List<Employee> getDummyEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John", "Doe", "john.doe@example.com"));
        employees.add(new Employee("Jane", "Smith", "jane.smith@example.com"));
        employees.add(new Employee("Bob", "Johnson", "bob.johnson@example.com"));
        return employees;
    }

    // Method to convert Employee object to JSON string
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
