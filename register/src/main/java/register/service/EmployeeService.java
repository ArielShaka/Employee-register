package register.service;

import org.springframework.stereotype.Service;
import register.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmployeeService {

    private final Map<String, Employee> employees = new ConcurrentHashMap<>();

    public boolean addEmployee(Employee employee) {
        String email = employee.getEmail();

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (employees.containsKey(email.trim().toLowerCase())) {
            return false;
        }
        employees.put(email, employee);
        return true;
    }


    public boolean removeEmployee(String email) {
        return employees.remove(email) != null;
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }
}
