package com.soebes.spring.example.employee;

import java.util.function.Function;

final class EmployeeMapper {

  private EmployeeMapper() {
  }

  static Function<Employee, EmployeeDTO> toDTO = employee -> new EmployeeDTO(
      employee.getId(),
      employee.getFirstName(),
      employee.getSurname(),
      EmployeeTypeDTO.valueOf(employee.getType().name())
  );

  static Function<EmployeeTypeDTO, EmployeeType> dtoTypeToJPAType = type -> EmployeeType.valueOf(type.name());
}
