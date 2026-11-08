package com.islam;

import java.util.*;

public class TaskService {
    private final Map<Integer, Task> tasks = new HashMap<>();
    private int nextId = 1;

    public Task add(String title, Priority priority) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title must not be blank");
        }
        Task task = new Task(nextId, title.strip(), priority, false);
        tasks.put(nextId, task);
        nextId++;
        return task;
    }

    public List<Task> findAll() {
        return tasks.values().stream().sorted(Comparator.comparing(Task::priority).reversed().thenComparing(Task::id)).toList();
    }

    public Task complete(int id) {
        Task task = tasks.get(id);
        if (task == null) {
            throw new TaskNotFoundException(id);
        }
        Task task1 = new Task(task.id(), task.title(), task.priority(), true);
        tasks.put(id, task1);
        return task1;
    }

    public Task delete(int id) {
        Task removed = tasks.remove(id);
        if (removed == null) {
            throw new TaskNotFoundException(id);
        }
        return removed;
    }

    public List<Task> findPending(){
        return findAll().stream().filter(task -> !task.done()).toList();
    }
}
