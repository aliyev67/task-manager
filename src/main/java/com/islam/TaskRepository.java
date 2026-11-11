package com.islam;

import java.util.List;

public interface TaskRepository {
    Task save(Task task);
    Task findById(int id);
    List<Task> findAll();
    Task deleteById(int id);
}
