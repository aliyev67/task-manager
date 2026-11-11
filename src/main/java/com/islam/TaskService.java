package com.islam;

import java.util.*;

public class TaskService {
    private final TaskRepository repository;
    private int nextId = 1;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task add(String title, Priority priority) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title must not be blank");
        }
        Task task = new Task(nextId, title.strip(), priority, false);
        repository.save(task);
        nextId++;
        return task;
    }

    public List<Task> findAll() {
        return repository.findAll().stream().sorted(Comparator.comparing(Task::priority).reversed().thenComparing(Task::id)).toList();
    }

    public Task complete(int id) {
        Task task = repository.findById(id);
        if (task == null) {
            throw new TaskNotFoundException(id);
        }
        Task task1 = new Task(task.id(), task.title(), task.priority(), true);
        repository.save(task);
        return task1;
    }

    public Task delete(int id) {
        Task removed = repository.deleteById(id);
        if (removed == null) {
            throw new TaskNotFoundException(id);
        }
        return removed;
    }

    public List<Task> findPending(){
        return findAll().stream().filter(task -> !task.done()).toList();
    }
}
