package com.soebes.spring.example;

import com.soebes.spring.example.employee.EmployeeDTO;
import com.soebes.spring.example.employee.EmployeeTypeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
final class ApiController {

  private final ApiService apiService;

  ApiController(ApiService apiService) {
    this.apiService = apiService;
  }

  @GetMapping("/api/employees/search/firstname/{firstname}")
  ResponseEntity<List<EmployeeDTO>> findByFirstNameContains(@PathVariable String firstname) {
    return apiService.findByFirstNameContains(firstname);
  }

  @GetMapping("/api/employees/search/surname/{surname}")
  ResponseEntity<List<EmployeeDTO>> findBySurnameContains(@PathVariable String surname) {
    return apiService.findBySurnameContains(surname);
  }

  @GetMapping("/api/employees/search/type/{type}")
  ResponseEntity<List<EmployeeDTO>> searchForType(@PathVariable EmployeeTypeDTO type) {
    return apiService.findByType(type);
  }


  @GetMapping("/api/employee/{id}")
  EmployeeDTO findById(@PathVariable("id") EmployeeDTO employeeDTO) {
    return employeeDTO;
  }

}
