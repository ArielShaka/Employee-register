package register.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import register.model.Employee;
import register.service.EmployeeService;
import register.utils.TestDataUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        // Clears the service before each test
        employeeService.getAllEmployees().clear();
    }

    @Test
    void addEmployee_ShouldReturnCreated_WhenEmployeeIsAdded() throws Exception {
        Employee employeeToAdd = TestDataUtil.getDummyEmployees().get(0);
        String employeeJson = TestDataUtil.asJsonString(employeeToAdd);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson))
                .andExpect(status().isCreated());
    }

    @Test
    void removeEmployee_ShouldReturnOk_WhenEmployeeIsRemoved() throws Exception {
        String email = TestDataUtil.getDummyEmployees().get(0).getEmail();


        Employee employeeToAdd = new Employee("John", "Doe", email);
        employeeService.addEmployee(employeeToAdd);

        mockMvc.perform(delete("/api/employees/" + email))
                .andExpect(status().isOk());
    }


    @Test
    void getAllEmployees_ShouldReturnListOfEmployees() throws Exception {
        List<Employee> dummyEmployees = TestDataUtil.getDummyEmployees();

        for (Employee employee : dummyEmployees) {
            employeeService.addEmployee(employee);
        }

        String expectedJson = TestDataUtil.asJsonString(dummyEmployees);

        mockMvc.perform(get("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }
}
