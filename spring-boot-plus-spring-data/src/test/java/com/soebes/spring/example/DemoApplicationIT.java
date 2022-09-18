package com.soebes.spring.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationIT {

	@Test
	void contextLoads() {
		assertThat(true).isTrue();
	}

}
