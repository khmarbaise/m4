package com.soebes.spring.example.employee;

public record EmployeeDTO(Integer id, String firstName, String surname, EmployeeTypeDTO type) {
}
