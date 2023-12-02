package com.aleksandarvasilevski.javaspringrestapi.models;
import java.time.LocalDateTime;

public class Task {
    private long id;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private boolean completed;

    // Constructors, getters, setters and other methods

    public Task(){
        // default constructor
    }

    public Task(Long id, String title, String description, LocalDateTime dueDate, boolean completed){
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    public Long getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }

    public LocalDateTime getDueDate(){
        return this.dueDate;
    }

    public boolean isCompleted(){
        return this.completed;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
