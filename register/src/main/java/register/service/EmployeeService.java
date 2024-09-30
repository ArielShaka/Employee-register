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
        if(employees.containsKey(employee.getEmail())) {
            return false;
        }
            employees.put(employee.getEmail(), employee);
            return true;
    }

    public boolean removeEmployee(String email) {
        if(!employees.containsKey(email)) {
            return false;
        }
        employees.remove(email);
        return true;
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }
}
