package com.aleksandarvasilevski.javaspringrestapi;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JavaSpringRestApiApplicationTests {
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void shouldReturnAllTasks() {
		ResponseEntity<String> response = restTemplate.getForEntity("/tasks", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void shouldReturnATaskById() {
		ResponseEntity<String> response = restTemplate.getForEntity("/tasks/1", String.class);
		// Expected OK status of the HTTP response
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		// Converts the response String into a JSON-aware object with lots of helper methods.
		DocumentContext documentContext = JsonPath.parse(response.getBody());
		// Expect a Task with id of 1
		Number id = documentContext.read("$.id");
		assertThat(id).isEqualTo(1);
	}

	@Test
	void shouldNotReturnATaskWithAnUnknownId() {
		ResponseEntity<String> response = restTemplate.getForEntity("/tasks/-1", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isBlank();
	}
}
