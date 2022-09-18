package com.soebes.spring.example.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
class Employee {

  @Id
  @GeneratedValue
  private Integer id;

  private String firstName;

  private String surname;

  @Enumerated(EnumType.STRING)
  private EmployeeType type;

  protected Employee() {
    // for JPA
  }

  Employee(Integer id, String firstName, String surname, EmployeeType type) {
    this.id = id;
    this.firstName = firstName;
    this.surname = surname;
    this.type = type;
  }

  Integer getId() {
    return id;
  }

  void setId(Integer id) {
    this.id = id;
  }

  String getFirstName() {
    return firstName;
  }

  void setFirstName(String name) {
    this.firstName = name;
  }

  String getSurname() {
    return surname;
  }

  void setSurname(String surname) {
    this.surname = surname;
  }

  EmployeeType getType() {
    return type;
  }

  void setType(EmployeeType type) {
    this.type = type;
  }


  @Override
  public String toString() {
    return "Employee{" +
           "id=" + id +
           ", firstName='" + firstName + '\'' +
           ", surname='" + surname + '\'' +
           ", type=" + type +
           '}';
  }
}
