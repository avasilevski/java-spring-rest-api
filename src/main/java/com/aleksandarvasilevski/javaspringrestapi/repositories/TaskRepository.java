package com.aleksandarvasilevski.javaspringrestapi.repositories;

import com.aleksandarvasilevski.javaspringrestapi.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
