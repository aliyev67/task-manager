package com.islam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    void findAllSortsByPriorityHighFirst() {
        TaskService service = new TaskService();
        Task task1 = service.add("a", Priority.LOW);
        Task task2 = service.add("b", Priority.HIGH);
        Task task3 = service.add("c", Priority.MEDIUM);
        Task task4 = service.add("d", Priority.HIGH);

        List<Task> list = service.findAll();

        assertEquals(List.of("b", "d", "c", "a"), list.stream().map(Task::title).toList());
    }

    @Test
    void completeMarksTaskDone() {
        TaskService service = new TaskService();
        Task task = service.add("a", Priority.LOW);

        Task completed = service.complete(task.id());

        assertTrue(completed.done());

        assertEquals(task.title(), completed.title());
        assertEquals(task.id(), completed.id());
    }

    @Test
    void completeUnknownIdThrows() {
        TaskService service = new TaskService();

        TaskNotFoundException e = assertThrows(TaskNotFoundException.class,
                () -> service.complete(999));

        assertEquals("Task not found: 999", e.getMessage());
    }
}
