package ru.daria.habitTracker.service;

import ru.daria.habitTracker.entities.Habit;
import ru.daria.habitTracker.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HabitServiceImpl implements HabitService {
    private final HabitRepository habitRepository;

    @Autowired
    public HabitServiceImpl(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    @Override
    public void createHabit(Habit habit) {
        habitRepository.create(habit);
    }

    @Override
    public Habit findHabitById(Long id) {
        return habitRepository.read(id);
    }

    @Override
    public List<Habit> findAllHabits() {
        return habitRepository.findAll();
    }

    @Override
    public void updateHabit(Habit habit) {
        habitRepository.update(habit);
    }

    @Override
    public void deleteHabit(Long id) {
        habitRepository.delete(id);
    }

    @Override
    public void incrementHabitCount(Long id) {
        habitRepository.incrementCount(id);
    }

    @Override
    public List<Habit> findActiveHabits() {
        return habitRepository.findAll().stream()
                .filter(Habit::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<Habit> findCompletedHabits() {
        return habitRepository.findAll().stream()
                .filter(Habit::isCompleted)
                .collect(Collectors.toList());
    }
}