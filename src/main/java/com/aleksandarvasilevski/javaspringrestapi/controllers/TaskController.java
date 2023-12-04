package com.aleksandarvasilevski.javaspringrestapi.controllers;

import com.aleksandarvasilevski.javaspringrestapi.models.Task;
import com.aleksandarvasilevski.javaspringrestapi.services.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }
    @GetMapping()
    public List<Task> getTasks(){
        return taskService.getTasks();
    }
}
