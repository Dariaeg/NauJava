package ru.daria.NauJava.service;

import ru.daria.NauJava.entities.Habit;

import java.util.List;

public interface HabitService {
    List<Habit> getUserHabits(Long userId);

    List<Habit> getActiveHabits(Long userId);

    List<Habit> getCompletedHabits(Long userId);

    List<Habit> getHabitsByCategory(Long categoryId, Long userId);

    Habit createHabit(Habit habit);

    Habit findHabitById(Long id);

    List<Habit> findAllHabits();

    Habit updateHabit(Habit habit);

    void deleteHabit(Long id);

    Habit incrementHabitCount(Long id);

    List<Habit> findActiveHabits();

    List<Habit> findCompletedHabits();

    List<Habit> getAllUserHabits(Long userId);

}