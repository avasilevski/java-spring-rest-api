package com.aleksandarvasilevski.javaspringrestapi.services;

import com.aleksandarvasilevski.javaspringrestapi.models.Task;
import com.aleksandarvasilevski.javaspringrestapi.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task getTaskById(long id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        // TODO: Handle this in case of not found, null
        return optionalTask.orElse(null);
    }
}
