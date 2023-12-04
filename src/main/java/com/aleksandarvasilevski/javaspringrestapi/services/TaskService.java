package com.aleksandarvasilevski.javaspringrestapi.services;

import com.aleksandarvasilevski.javaspringrestapi.models.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Arrays;

@Service
public class TaskService {
    public List<Task> getTasks(){
        // Temp hardcoded data
        Task task1 = new Task(01L, "Task 1", "Foo", false);
        Task task2 = new Task(02L, "Task 2", "Foo", false);
        return Arrays.asList(task1, task2);
    }
}
