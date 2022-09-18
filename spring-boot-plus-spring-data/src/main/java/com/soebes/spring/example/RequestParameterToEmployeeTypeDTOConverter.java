package com.soebes.spring.example;

import com.soebes.spring.example.employee.EmployeeTypeDTO;
import org.springframework.core.convert.converter.Converter;

public class RequestParameterToEmployeeTypeDTOConverter implements Converter<String, EmployeeTypeDTO> {

  @Override
  public EmployeeTypeDTO convert(String source) {
    return EmployeeTypeDTO.valueOf(source.toUpperCase());
  }
}