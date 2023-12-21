package com.aleksandarvasilevski.javaspringrestapi.exceptions;

public class TaskException extends RuntimeException {
    public TaskException(String message) {
        super(message);
    }
}
