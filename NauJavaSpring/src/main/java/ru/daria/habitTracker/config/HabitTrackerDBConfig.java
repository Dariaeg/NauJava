package ru.daria.habitTracker.config;

import ru.daria.habitTracker.entities.Habit;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class HabitTrackerDBConfig {
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public List<Habit> habitContainer() {
        List<Habit> habits = new ArrayList<>();
        //Тестовые данные
        habits.add(new Habit(1L, "Утренняя зарядка", "Делать зарядку каждое утро",
                30, 15, true));
        habits.add(new Habit(2L, "Чтение книг", "Читать 30 минут в день",
                20, 20,  false));
        habits.add(new Habit(3L, "Пить воду", "Выпивать 2 литра воды в день",
                31, 10, true));
        return habits;
    }
}