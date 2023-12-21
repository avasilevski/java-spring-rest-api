package com.aleksandarvasilevski.javaspringrestapi.services;

import com.aleksandarvasilevski.javaspringrestapi.exceptions.TaskException;
import com.aleksandarvasilevski.javaspringrestapi.models.Task;
import com.aleksandarvasilevski.javaspringrestapi.repositories.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

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

        // If the task is not found, throw an exception or handle it differently
        return optionalTask.orElseThrow(() -> new TaskException("Task not found with ID: " + id));
    }

    public Task save(Task task){
        return taskRepository.save(task);
    }
}
