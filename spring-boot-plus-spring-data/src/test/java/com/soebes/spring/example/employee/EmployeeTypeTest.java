package com.soebes.spring.example.employee;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeTypeTest {

  @Test
  void check_that_order_and_element_names_are_identical() {
    var jpaEmployeeType = Arrays.stream(EmployeeType.values()).map(EmployeeType::name).toList();
    var dtoEmployeeType = Arrays.stream(EmployeeTypeDTO.values()).map(EmployeeTypeDTO::name).toList();
    assertThat(jpaEmployeeType).containsExactlyElementsOf(dtoEmployeeType);
  }
}
