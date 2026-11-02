package com.islam;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {
    @Test
    void addBlankTitleThrows() {
        TaskService service = new TaskService();
        assertThrows(IllegalArgumentException.class, () -> service.add("   ", Priority.LOW));
    }

    @Test
    void addReturnsTaskWithFirstId() {
        TaskService service = new TaskService();
        Task task = service.add("Купить хлеб", Priority.HIGH);
        assertEquals(1, task.id());
        assertEquals("Купить хлеб", task.title());
        assertEquals(Priority.HIGH, task.priority());
        assertFalse(task.done());
    }

    @Test
    void addStripsTitle() {
        TaskService service = new TaskService();
        Task task = service.add("  хлеб  ", Priority.MEDIUM);
        assertEquals("хлеб", task.title());
    }
}
