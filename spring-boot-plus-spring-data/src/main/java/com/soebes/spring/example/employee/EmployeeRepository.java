package com.soebes.spring.example.employee;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

interface EmployeeRepository extends CrudRepository<Employee, Integer> {

  List<Employee> findByFirstNameContains(String name);

  List<Employee> findBySurnameContains(String surname);

  List<Employee> findByType(EmployeeType type);

}
