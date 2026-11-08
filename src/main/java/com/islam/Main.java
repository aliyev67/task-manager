package com.islam;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskService service = new TaskService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Добавить  2. Список  3. Невыполненные  4. Выполнить  5. Удалить  0. Выход");
            System.out.print("> ");
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1" -> {
                        System.out.print("Название: ");
                        String title = scanner.nextLine();
                        System.out.print("Приоритет (LOW/MEDIUM/HIGH): ");
                        Priority priority = Priority.valueOf(scanner.nextLine().strip().toUpperCase());
                        System.out.println("Добавлена: " + service.add(title, priority));
                    }
                    case "2" -> {
                        if (service.findAll().isEmpty()){
                            System.out.println("Задач нет(");
                        }
                        service.findAll().forEach(t ->
                                System.out.println(format(t)));
                    }
                    case "3" -> {
                        service.findPending().forEach(t ->
                                System.out.println(format(t)));
                    }
                    case "4" -> {
                        System.out.print("Номер задачи: ");
                        int id = Integer.parseInt(scanner.nextLine().strip());
                        System.out.println("Выполнена: " + service.complete(id));
                    }
                    case "5" -> {
                        System.out.print("Номер задачи: ");
                        int id = Integer.parseInt(scanner.nextLine().strip());
                        System.out.println("Удалена: " + service.delete(id));
                    }
                    case "0" -> {
                        System.out.println("Пока!");
                        return;
                    }
                    default -> System.out.println("Нет такого пункта");
                }
            } catch (TaskNotFoundException | IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private static String format(Task t) {
        return "[" + t.id() + "] " + t.title() + " (" + t.priority() + ")" + (t.done() ? " ✓" : "");
    }
}