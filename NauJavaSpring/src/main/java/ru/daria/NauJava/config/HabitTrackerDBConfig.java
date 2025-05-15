package ru.daria.NauJava.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.daria.NauJava.entities.*;
import ru.daria.NauJava.repository.*;

@Configuration
public class HabitTrackerDBConfig {

    @Bean
    public CommandLineRunner initDatabase(
            UserRepository userRepository,
            CategoryRepository categoryRepository,
            HabitRepository habitRepository) {

        return args -> {
            // Создаем тестового пользователя
            User user = new User();
            user.setUsername("testuser");
            user.setPassword("password");
            user = userRepository.save(user);

            // Создаем тестовые категории
            Category healthCategory = new Category();
            healthCategory.setName("Здоровье");
            healthCategory = categoryRepository.save(healthCategory);

            Category studyCategory = new Category();
            studyCategory.setName("Учеба");
            studyCategory = categoryRepository.save(studyCategory);

            // Создаем тестовые привычки
            Habit waterHabit = new Habit();
            waterHabit.setName("Пить воду");
            waterHabit.setDescription("Выпивать 2 литра воды в день");
            waterHabit.setTargetCount(30);
            waterHabit.setUser(user);
            waterHabit.setCategory(healthCategory);
            habitRepository.save(waterHabit);

            Habit studyHabit = new Habit();
            studyHabit.setName("Изучать Spring");
            studyHabit.setDescription("Заниматься 1 час в день");
            studyHabit.setTargetCount(20);
            studyHabit.setUser(user);
            studyHabit.setCategory(studyCategory);
            habitRepository.save(studyHabit);
        };
    }
}