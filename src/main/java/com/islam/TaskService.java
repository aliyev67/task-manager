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

    public List<Task> findAll(){
        return tasks.values().stream().sorted(Comparator.comparing(Task::priority).reversed().thenComparing(Task::id)).toList();
    }
}
