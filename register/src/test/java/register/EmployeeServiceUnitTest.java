package register;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import register.model.Employee;
import register.service.EmployeeService;
import register.utils.TestDataUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceUnitTest {

    private EmployeeService employeeService;
    private List<Employee> dummyEmployees;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService();
        dummyEmployees = TestDataUtil.getDummyEmployees();
    }

    @Test
    void addEmployee_ShouldReturnTrue_WhenEmployeeIsAdded() {
        Employee employeeToAdd = dummyEmployees.get(0);

        boolean result = employeeService.addEmployee(employeeToAdd);

        assertTrue(result);
        List<Employee> allEmployees = employeeService.getAllEmployees();
        assertTrue(allEmployees.contains(employeeToAdd));
    }

    @Test
    void addEmployee_ShouldReturnFalse_WhenEmployeeAlreadyExists() {
        Employee employeeToAdd = dummyEmployees.get(0);

        employeeService.addEmployee(employeeToAdd);

        boolean result = employeeService.addEmployee(employeeToAdd);

        assertFalse(result);
    }

    @Test
    void removeEmployee_ShouldReturnTrue_WhenEmployeeIsRemoved() {
        Employee employeeToAdd = dummyEmployees.get(1);

        employeeService.addEmployee(employeeToAdd);

        boolean result = employeeService.removeEmployee(employeeToAdd.getEmail());

        assertTrue(result);
        List<Employee> allEmployees = employeeService.getAllEmployees();
        assertFalse(allEmployees.contains(employeeToAdd));
    }

    @Test
    void removeEmployee_ShouldReturnFalse_WhenEmployeeDoesNotExist() {
        Employee employeeToRemove = dummyEmployees.get(1);

        boolean result = employeeService.removeEmployee(employeeToRemove.getEmail());

        assertFalse(result);
    }

    @Test
    void getAllEmployees_ShouldReturnListOfEmployees() {
        for (Employee employee : dummyEmployees) {
            employeeService.addEmployee(employee);
        }

        List<Employee> employees = employeeService.getAllEmployees();

        assertEquals(3, employees.size());
        assertTrue(employees.containsAll(dummyEmployees));
    }
}
