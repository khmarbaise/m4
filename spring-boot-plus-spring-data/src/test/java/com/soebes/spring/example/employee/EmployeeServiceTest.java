package com.soebes.spring.example.employee;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

  @Mock
  private EmployeeRepository employeeRepository;

  @InjectMocks
  private EmployeeService employeeService;

  @Nested
  class FindByNameContains {
    @Test
    void nothing() {
      var byNameContains = employeeService.findByFirstNameContains("Nothing");
      assertThat(byNameContains).isEmpty();
    }

    @Test
    void returnValue() {
      var givenValue1 = new Employee(12,"firstName1", "surName1", EmployeeType.GROUP_LEAD);
      var givenValue2 = new Employee(13,"firstName2", "surName2", EmployeeType.TEAM_LEAD);
      when(employeeRepository.findByFirstNameContains(any())).thenReturn(List.of(givenValue1, givenValue2));

      assertThat(employeeService.findByFirstNameContains("anton"))
          .containsExactly(EmployeeMapper.toDTO.apply(givenValue1), EmployeeMapper.toDTO.apply(givenValue2));
    }

  }

  @Nested
  class FindById {
    @Test
    void nothing() {
      assertThat(employeeService.findById(0)).isEmpty();
    }

    @Test
    void returnValue() {
      var givenValue = new Employee(12,"firstName", "surName1", EmployeeType.GROUP_LEAD);
      when(employeeRepository.findById(12)).thenReturn(Optional.of(givenValue));

      var expectedEmployeeDTO = EmployeeMapper.toDTO.apply(givenValue);

      assertThat(employeeService.findById(12)).hasValue(expectedEmployeeDTO);
    }
  }

  @Nested
  class FindByType {

    @Test
    void nothing() {
      assertThat(employeeService.findByType(EmployeeTypeDTO.MANAGER)).isEmpty();
    }

    @Test
    void returnValue() {
      var givenValue = new Employee(6781, "tl1", "surName1", EmployeeType.TEAM_LEAD);
      when(employeeRepository.findByType(EmployeeType.TEAM_LEAD)).thenReturn(List.of(givenValue));

      var calculatedList = employeeService.findByType(EmployeeTypeDTO.TEAM_LEAD);

      var expectedDTO = new EmployeeDTO(6781, "tl1", "surName1", EmployeeTypeDTO.TEAM_LEAD);
      assertThat(calculatedList)
          .containsExactlyElementsOf(List.of(expectedDTO));
    }
  }
}