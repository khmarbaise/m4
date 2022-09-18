package com.soebes.spring.example;

import com.soebes.spring.example.employee.EmployeeDTO;
import com.soebes.spring.example.employee.EmployeeService;
import com.soebes.spring.example.employee.EmployeeTypeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
final class ApiService {

  private final EmployeeService employeeService;

  ApiService(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  ResponseEntity<EmployeeDTO> findById(Integer id) {
    return ResponseEntity.of(employeeService.findById(id));
  }

  ResponseEntity<List<EmployeeDTO>> findByFirstNameContains(String name) {
    return response(employeeService.findByFirstNameContains(name));
  }

  ResponseEntity<List<EmployeeDTO>> findBySurnameContains(String name) {
    return response(employeeService.findBySurnameContains(name));
  }

  ResponseEntity<List<EmployeeDTO>> findByType(EmployeeTypeDTO type) {
    return response(employeeService.findByType(type));
  }

  private <T> ResponseEntity<List<T>> response(List<T> list) {
    if (list.isEmpty()) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(list);
    }
  }
}
