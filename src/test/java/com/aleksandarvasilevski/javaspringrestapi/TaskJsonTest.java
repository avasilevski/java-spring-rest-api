package com.aleksandarvasilevski.javaspringrestapi;

import com.aleksandarvasilevski.javaspringrestapi.models.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class TaskJsonTest {

    @Autowired
    private JacksonTester<Task> json;

    @Test
    void taskSerializationTest() throws IOException {
        Task task = new Task(1L, "Foo", "Foo something...");
        assertThat(json.write(task)).isStrictlyEqualToJson("expected.json");
        assertThat(json.write(task)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(task)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(1);
        assertThat(json.write(task)).hasJsonPathStringValue("@.title");
        assertThat(json.write(task)).extractingJsonPathStringValue("@.title")
                .isEqualTo("Foo");
        assertThat(json.write(task)).hasJsonPathStringValue("@.description");
        assertThat(json.write(task)).extractingJsonPathStringValue("@.description")
                .isEqualTo("Foo something...");
        assertThat(json.write(task)).hasJsonPathBooleanValue("@.completed");
        assertThat(json.write(task)).extractingJsonPathBooleanValue("@.completed")
                .isEqualTo(false);
    }

    @Test
    void taskDeserializationTest() throws IOException {
        String expected = """
           {
              "id":1,
              "title": "Foo",
              "description": "Foo something...",
              "completed": false
           }
           """;
        assertThat(json.parse(expected)).isEqualToComparingFieldByField(new Task(1L, "Foo", "Foo something..."));
        assertThat(json.parseObject(expected).getId()).isEqualTo(1);
        assertThat(json.parseObject(expected).getTitle()).isEqualTo("Foo");
        assertThat(json.parseObject(expected).getDescription()).isEqualTo("Foo something...");
        assertThat(json.parseObject(expected).isCompleted()).isEqualTo(false);
    }
}

