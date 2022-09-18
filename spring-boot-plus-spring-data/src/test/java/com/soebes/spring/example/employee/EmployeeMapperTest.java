package com.soebes.spring.example.employee;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class EmployeeMapperTest {

  @Test
  void jpaFieldNamesAndDTOFieldNamesAreTheSame() {
    var jpaDeclaredFields = Arrays.stream(Employee.class.getDeclaredFields()).map(Field::getName).toList();
    var dtoDeclaredFields = Arrays.stream(EmployeeDTO.class.getDeclaredFields()).map(Field::getName).toList();
    assertThat(jpaDeclaredFields).containsExactlyElementsOf(dtoDeclaredFields);
  }

  @Test
  void mapEmployeeToEmployeeDTO() {
    var employee = new Employee();
    employee.setId(11);
    employee.setFirstName("firstName");
    employee.setSurname("surname");
    employee.setType(EmployeeType.GROUP_LEAD);

    assertThat(EmployeeMapper.toDTO.apply(employee)).satisfies(dto -> {
      assertThat(dto.id()).isEqualTo(11);
      assertThat(dto.firstName()).isEqualTo("firstName");
      assertThat(dto.surname()).isEqualTo("surname");
      assertThat(dto.type()).isEqualTo(EmployeeTypeDTO.GROUP_LEAD);
    });
  }
}