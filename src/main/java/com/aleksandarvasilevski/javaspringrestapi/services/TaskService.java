package com.aleksandarvasilevski.javaspringrestapi.services;

import com.aleksandarvasilevski.javaspringrestapi.models.Task;
import com.aleksandarvasilevski.javaspringrestapi.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Arrays;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
}
