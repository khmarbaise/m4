package com.soebes.spring.example.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static com.soebes.spring.example.employee.EmployeeMapper.dtoTypeToJPAType;
import static com.soebes.spring.example.employee.EmployeeMapper.toDTO;

@Service
public class EmployeeService {

  private final EmployeeRepository employeeRepository;

  private static final Logger LOG = LoggerFactory.getLogger(EmployeeService.class);

  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public List<EmployeeDTO> findByFirstNameContains(String firstName) {
    return employeeRepository.findByFirstNameContains(firstName).stream()
        .map(toDTO).toList();
  }

  public List<EmployeeDTO> findBySurnameContains(String surename) {
    return employeeRepository.findBySurnameContains(surename).stream()
        .map(toDTO).toList();
  }

  public Optional<EmployeeDTO> findById(Integer id) {
    return employeeRepository.findById(id).map(toDTO);
  }

  public List<EmployeeDTO> findByType(EmployeeTypeDTO type) {
    return employeeRepository
        .findByType(dtoTypeToJPAType.apply(type))
        .stream()
        .map(toDTO)
        .toList();
  }

  @EventListener
  void ready(ApplicationReadyEvent event) {
    LOG.info("ApplicationReadyEvent received. start {}", event.getTimeTaken());
    StreamSupport.stream(employeeRepository.findAll().spliterator(), true).forEach(
        s -> LOG.info(" id: {} firstName: {} surname: {} type: {}", s.getId(), s.getFirstName(), s.getSurname(), s.getType())
    );
    LOG.info("ApplicationReadyEvent received. end.");
  }
}
