package register.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import register.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class TestDataUtil {

    public static List<Employee> getDummyEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("john.doe@example.com", "John", "Doe"));
        employees.add(new Employee("jane.smith@example.com", "Jane", "Smith"));
        employees.add(new Employee("bob.johnson@example.com", "Bob", "Johnson"));
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
