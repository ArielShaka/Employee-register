package register;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import register.controller.EmployeeController;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddEmployee() throws Exception {
        String newEmployeeJson = "{\"firstName\":\"John\", \"lastName\":\"Doe\", \"email\":\"john.doe@example.com\"}";

        mockMvc.perform(post("/api/employees")
                        .contentType("application/json")
                        .content(newEmployeeJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        // Assume an employee with email "test@example.com" exists
        mockMvc.perform(delete("/api/employees/test@example.com"))
                .andExpect(status().isOk());
    }
}
