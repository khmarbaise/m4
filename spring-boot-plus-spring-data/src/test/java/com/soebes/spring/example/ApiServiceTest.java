package com.soebes.spring.example;

import com.soebes.spring.example.employee.EmployeeDTO;
import com.soebes.spring.example.employee.EmployeeService;
import com.soebes.spring.example.employee.EmployeeTypeDTO;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApiServiceTest {

  @Mock
  private EmployeeService employeeService;

  @InjectMocks
  private ApiService apiService;

  @Nested
  class FindById {
    @Test
    void emptyAnswer() {
      when(employeeService.findById(12)).thenReturn(Optional.empty());
      assertThat(apiService.findById(12)).isEqualTo(ResponseEntity.of(Optional.empty()));
    }

    @Test
    void nonEmptyAnswer() {
      var employeeDTO = new EmployeeDTO(234, "firstName", "surname", EmployeeTypeDTO.GROUP_LEAD);
      when(employeeService.findById(234)).thenReturn(Optional.of(employeeDTO));
      assertThat(apiService.findById(234)).isEqualTo(ResponseEntity.of(Optional.of(employeeDTO)));
    }
  }

  @Nested
  class FindByNameContains {
    @Test
    void emptyAnswer() {
      when(employeeService.findByFirstNameContains("first")).thenReturn(List.of());
      assertThat(apiService.findByFirstNameContains("first")).isEqualTo(ResponseEntity.notFound().build());
    }

    @Test
    void nonEmptyAnswer() {
      var employeeDTO = new EmployeeDTO(234, "firstName", "surname", EmployeeTypeDTO.GROUP_LEAD);
      when(employeeService.findByFirstNameContains("first")).thenReturn(List.of(employeeDTO));
      assertThat(apiService.findByFirstNameContains("first")).isEqualTo(ResponseEntity.ok(List.of(employeeDTO)));
    }
  }

  @Nested
  class FindByType {

    @Test
    void emptyAnswer() {
      when(employeeService.findByType(EmployeeTypeDTO.TEAM_LEAD)).thenReturn(List.of());
      assertThat(apiService.findByType(EmployeeTypeDTO.TEAM_LEAD)).isEqualTo(ResponseEntity.notFound().build());
    }

    @Test
    void nonEmptyAnswer() {
      var employeeDTO = new EmployeeDTO(234, "firstName", "surname", EmployeeTypeDTO.TEAM_LEAD);
      when(employeeService.findByType(EmployeeTypeDTO.TEAM_LEAD)).thenReturn(List.of(employeeDTO));
      assertThat(apiService.findByType(EmployeeTypeDTO.TEAM_LEAD)).isEqualTo(ResponseEntity.ok(List.of(employeeDTO)));
    }
  }

}