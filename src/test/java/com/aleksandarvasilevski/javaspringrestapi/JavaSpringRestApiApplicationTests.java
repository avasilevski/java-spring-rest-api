package com.aleksandarvasilevski.javaspringrestapi;

import com.aleksandarvasilevski.javaspringrestapi.models.Task;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.net.URI;

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
		ResponseEntity<String> response = restTemplate.getForEntity("/tasks/999", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isBlank();
	}

	@Test
	void shouldCreateANewTask() {
		Task task = new Task(1, "Task 1", "This is a sample task description");
		ResponseEntity<Void> createResponse = restTemplate.postForEntity("/tasks", task, Void.class);
		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		URI locationOfNewTask = createResponse.getHeaders().getLocation();
		ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewTask, String.class);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
		Number id = documentContext.read("$.id");
		String title = documentContext.read("$.title");
		String description = documentContext.read("$.description");
		boolean completed = documentContext.read("$.completed");

		assertThat(id).isNotNull();
		assertThat(title).isEqualTo("Task 1");
		assertThat(description).isEqualTo("This is a sample task description");
		assertThat(completed).isEqualTo(false);
	}
}
