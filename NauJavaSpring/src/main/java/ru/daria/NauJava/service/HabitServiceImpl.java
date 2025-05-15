package ru.daria.NauJava.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.daria.NauJava.entities.Habit;
import ru.daria.NauJava.repository.HabitRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public abstract class HabitServiceImpl implements HabitService {
    private HabitRepository habitRepository;

    @Override
    @Transactional
    public Habit createHabit(Habit habit) {
        habit.setActive(true);
        habitRepository.save(habit);
        return habit;
    }

    @Override
    @Transactional
    public Habit updateHabit(Habit habit) {
        habitRepository.save(habit);
        return habit;
    }

    @Override
    @Transactional
    public void deleteHabit(Long id) {
        habitRepository.deleteById(id);
    }

    @Transactional
    public Habit toggleHabitStatus(Long id) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found"));
        habit.setActive(habit.isActive());
        return habitRepository.save(habit);
    }

    @Transactional
    public Habit incrementHabitCounter(Long id) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found"));

        if (habit.isActive()) {
            habit.incrementCount();
            return habitRepository.save(habit);
        }
        throw new IllegalStateException("Cannot increment inactive habit");
    }

    public List<Habit> getAllUserHabits(Long userId) {
        return habitRepository.findByUserId(userId);
    }

    public List<Habit> getActiveUserHabits(Long userId) {
        return habitRepository.findByActiveTrueAndUserId(userId);
    }

    public List<Habit> getCompletedUserHabits(Long userId) {
        return habitRepository.findByActiveTrueAndCompletedTrueAndUserId(userId);
    }

    public List<Habit> getActiveHabitsByCategory(Long categoryId, Long userId) {
        return habitRepository.findActiveByCategoryAndUser(categoryId, userId);
    }
}