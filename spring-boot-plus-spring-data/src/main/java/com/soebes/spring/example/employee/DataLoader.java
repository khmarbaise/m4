package com.soebes.spring.example.employee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static com.soebes.spring.example.employee.EmployeeType.GROUP_LEAD;
import static com.soebes.spring.example.employee.EmployeeType.MANAGER;
import static com.soebes.spring.example.employee.EmployeeType.TEAM_LEAD;

@Component
record DataLoader() {

	@Bean
  CommandLineRunner loadData(EmployeeRepository repository) {
		return args -> {
			repository.save(new Employee(0, "Frodo", "Baggins", MANAGER));
			repository.save(new Employee(0, "Bilbo", "Baggins", GROUP_LEAD));
			repository.save(new Employee(0, "Samwise", "Gamgee", TEAM_LEAD));
		};
	}
}
