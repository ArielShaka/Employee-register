package register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import register.model.Employee;
import register.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        boolean isAdded = employeeService.addEmployee(employee);

        if (isAdded) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Employee added successfully");
        } else {
            return ResponseEntity.badRequest().body("Employee with this email already exists");
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> removeEmployee(@PathVariable String email) {
        boolean isRemoved = employeeService.removeEmployee(email);

        if (isRemoved) {
            return ResponseEntity.ok("Employee removed successfully");
        } else {
            return ResponseEntity.badRequest().body("Employee with this email does not exist");
        }
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
}
