package ru.daria.NauJava.config;

import ru.daria.NauJava.service.HabitService;
import ru.daria.NauJava.entities.Habit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class ConsoleConfig {
    @Bean
    public CommandLineRunner commandLineRunner(@Autowired HabitService habitService) {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Трекер привычек. Введите команду (help - список команд):");

            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine().trim();

                if ("exit".equalsIgnoreCase(input)) {
                    System.out.println("Выход из программы...");
                    break;
                }

                processCommand(input, habitService, scanner);
            }

            scanner.close();
        };
    }

    private void processCommand(String input, HabitService habitService, Scanner scanner) {
        String[] parts = input.split(" ");
        String command = parts[0].toLowerCase();

        try {
            switch (command) {
                case "help":
                    printHelp();
                    break;
                case "list":
                    listHabits(habitService);
                    break;
                case "create":
                    createHabit(habitService, scanner);
                    break;
                case "update":
                    updateHabit(habitService, scanner);
                    break;
                case "delete":
                    deleteHabit(habitService, scanner);
                    break;
                case "increment":
                    incrementHabit(habitService, scanner);
                    break;
                case "active":
                    listActiveHabits(habitService);
                    break;
                case "completed":
                    listCompletedHabits(habitService);
                    break;
                default:
                    System.out.println("Неизвестная команда. Введите 'help' для списка команд.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void printHelp() {
        System.out.println("Доступные команды:");
        System.out.println("help - показать эту справку");
        System.out.println("list - список всех привычек");
        System.out.println("create - создать новую привычку");
        System.out.println("update - обновить привычку");
        System.out.println("delete - удалить привычку");
        System.out.println("increment - увеличить счетчик выполнений");
        System.out.println("active - список активных привычек");
        System.out.println("completed - список завершенных привычек");
        System.out.println("exit - выход из программы");
    }

    private void listHabits(HabitService habitService) {
        System.out.println("Список всех привычек:");
        habitService.findAllHabits().forEach(System.out::println);
    }

    private void createHabit(HabitService habitService, Scanner scanner) {
        System.out.println("Создание новой привычки:");

        Habit habit = new Habit();

        System.out.print("Введите название: ");
        habit.setName(scanner.nextLine());

        System.out.print("Введите описание: ");
        habit.setDescription(scanner.nextLine());

        System.out.print("Введите целевое количество выполнений: ");
        habit.setTargetCount(Integer.parseInt(scanner.nextLine()));

        System.out.print("Введите текущее количество выполнений: ");
        habit.setCurrentCount(Integer.parseInt(scanner.nextLine()));

        System.out.print("Активна ли привычка (true/false): ");
        habit.setActive(Boolean.parseBoolean(scanner.nextLine()));

        habitService.createHabit(habit);
        System.out.println("Привычка успешно создана!");
    }

    private void updateHabit(HabitService habitService, Scanner scanner) {
        System.out.print("Введите ID привычки для обновления: ");
        Long id = Long.parseLong(scanner.nextLine());
        Habit habit = habitService.findHabitById(id);

        if (habit == null) {
            System.out.println("Привычка с ID " + id + " не найдена.");
            return;
        }

        System.out.println("Текущие данные:");
        System.out.println(habit);

        System.out.print("Введите новое название (оставьте пустым, чтобы не менять): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) habit.setName(name);

        System.out.print("Введите новое описание (оставьте пустым, чтобы не менять): ");
        String description = scanner.nextLine();
        if (!description.isEmpty()) habit.setDescription(description);

        System.out.print("Введите новое целевое количество (оставьте пустым, чтобы не менять): ");
        String targetCountStr = scanner.nextLine();
        if (!targetCountStr.isEmpty()) habit.setTargetCount(Integer.parseInt(targetCountStr));

        System.out.print("Введите новое текущее количество (оставьте пустым, чтобы не менять): ");
        String currentCountStr = scanner.nextLine();
        if (!currentCountStr.isEmpty()) habit.setCurrentCount(Integer.parseInt(currentCountStr));

        System.out.print("Изменить активность (true/false, оставьте пустым, чтобы не менять): ");
        String activeStr = scanner.nextLine();
        if (!activeStr.isEmpty()) habit.setActive(Boolean.parseBoolean(activeStr));

        habitService.updateHabit(habit);
        System.out.println("Привычка успешно обновлена!");
    }

    private void deleteHabit(HabitService habitService, Scanner scanner) {
        System.out.print("Введите ID привычки для удаления: ");
        Long id = Long.parseLong(scanner.nextLine());
        habitService.deleteHabit(id);
        System.out.println("Привычка успешно удалена!");
    }

    private void incrementHabit(HabitService habitService, Scanner scanner) {
        System.out.print("Введите ID привычки для увеличения счетчика: ");
        Long id = Long.parseLong(scanner.nextLine());
        habitService.incrementHabitCount(id);
        System.out.println("Счетчик привычки увеличен!");
    }

    private void listActiveHabits(HabitService habitService) {
        System.out.println("Список активных привычек:");
        habitService.findActiveHabits().forEach(System.out::println);
    }

    private void listCompletedHabits(HabitService habitService) {
        System.out.println("Список завершенных привычек:");
        habitService.findCompletedHabits().forEach(System.out::println);
    }
}
