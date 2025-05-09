package ru.daria.habitTracker.service;

import ru.daria.habitTracker.entities.Habit;
import java.util.List;

public interface HabitService {
    void createHabit(Habit habit);
    Habit findHabitById(Long id);
    List<Habit> findAllHabits();
    void updateHabit(Habit habit);
    void deleteHabit(Long id);
    void incrementHabitCount(Long id);
    List<Habit> findActiveHabits();
    List<Habit> findCompletedHabits();
}