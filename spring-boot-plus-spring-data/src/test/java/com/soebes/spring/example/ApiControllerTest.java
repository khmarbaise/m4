package com.soebes.spring.example;

import com.soebes.spring.example.employee.EmployeeDTO;
import com.soebes.spring.example.employee.EmployeeTypeDTO;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static com.soebes.spring.example.employee.EmployeeTypeDTO.GROUP_LEAD;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApiController.class)
class ApiControllerTest {

  @MockBean
  private ApiService apiService;

  @Autowired
  private MockMvc mockMvc;

  @Nested
  class EmployeesSearchFirstName {

    @Test
    void expectedContent() throws Exception {
      var employeeDTO = new EmployeeDTO(1234, "search_name", "surname", EmployeeTypeDTO.MANAGER);
      when(apiService.findByFirstNameContains("search_name")).thenReturn(ResponseEntity.ok(List.of(employeeDTO)));
      mockMvc
          .perform(get("/api/employees/search/firstname/search_name"))
          .andExpect(content()
              .json("""
                  [
                    {
                      "id": 1234,
                      "firstName": "search_name",
                      "surname": "surname",
                      "type": "MANAGER"
                    }
                  ]
                  """)
          )
          .andExpect(status().is2xxSuccessful());
    }

    @Test
    void emptyContent() throws Exception {
      when(apiService.findByFirstNameContains("search_name")).thenReturn(ResponseEntity.ok(List.of()));
      mockMvc
          .perform(get("/api/employees/search/firstname/search_name"))
          .andExpect(content()
              .json("""
                  [
                  ]
                  """)
          )
          .andExpect(status().is2xxSuccessful());
    }
  }

  @Nested
  class EmployeesSearchSurname {

    @Test
    void expectedContent() throws Exception {
      var employeeDTO = new EmployeeDTO(1234, "search_name", "surname", EmployeeTypeDTO.MANAGER);
      when(apiService.findBySurnameContains("search_name")).thenReturn(ResponseEntity.ok(List.of(employeeDTO)));
      mockMvc
          .perform(get("/api/employees/search/surname/search_name"))
          .andExpect(content()
              .json("""
                  [
                    {
                      "id": 1234,
                      "firstName": "search_name",
                      "surname": "surname",
                      "type": "MANAGER"
                    }
                  ]
                  """)
          )
          .andExpect(status().is2xxSuccessful());
    }

    @Test
    void emptyContent() throws Exception {
      when(apiService.findBySurnameContains("search_name")).thenReturn(ResponseEntity.ok(List.of()));
      mockMvc
          .perform(get("/api/employees/search/surname/search_name"))
          .andExpect(content()
              .json("""
                  [
                  ]
                  """)
          )
          .andExpect(status().is2xxSuccessful());
    }
  }

  @Nested
  class EmployeesSearchType {

    @Test
    void expectedContent() throws Exception {
      var employeeDTO = new EmployeeDTO(1234, "search_type", "Role", EmployeeTypeDTO.MANAGER);
      when(apiService.findByType(EmployeeTypeDTO.MANAGER)).thenReturn(ResponseEntity.ok(List.of(employeeDTO)));
      mockMvc
          .perform(get("/api/employees/search/type/MANAGER"))
          .andExpect(content()
              .json("""
                  [
                    {
                      "id": 1234,
                      "firstName": "search_type",
                      "surname": "Role",
                      "type": "MANAGER"
                    }
                  ]
                  """)
          )
          .andExpect(status().is2xxSuccessful());
    }

    @Test
    void emptyContent() throws Exception {
      when(apiService.findByType(GROUP_LEAD)).thenReturn(ResponseEntity.ok(List.of()));
      mockMvc
          .perform(get("/api/employees/search/type/GROUP_LEAD"))
          .andExpect(content()
              .json("""
                  [
                  ]
                  """)
          )
          .andExpect(status().is2xxSuccessful());
    }
  }

  @Nested
  class Employee {
    @Test
    void expectedContent() throws Exception {
      var employeeDTO = new EmployeeDTO(1, "Name", "Role", GROUP_LEAD);
      when(apiService.findById(1)).thenReturn(ResponseEntity.of(Optional.of(employeeDTO)));
      mockMvc
          .perform(get("/api/employee/1"))
          .andExpect(content()
              .json("""
                  {
                    "id": 1,
                    "firstName": "Name",
                    "surname": "Role",
                    "type": "GROUP_LEAD"
                  }
                  """)
          )
          .andExpect(status().is2xxSuccessful());
    }

    @Test
    void emptyContent() throws Exception {
      when(apiService.findById(1)).thenReturn(ResponseEntity.of(Optional.empty()));
      mockMvc
          .perform(get("/api/employee/1"))
          .andExpect(status().isNotFound());
    }

  }


}