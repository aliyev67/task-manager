package com.islam;

import java.util.HashMap;
import java.util.Map;

public class TaskService {
    Map<Integer, Task> tasks = new HashMap<>();
    static int nextId = 1;

    public Task add (String title, Priority priority) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title must not be blank");
        }
        Task task = new Task(nextId, title, priority, false);
        tasks.put(nextId, task);
        nextId++;
        return task;
    }
}
