package ru.daria.habitTracker.repository;

import ru.daria.habitTracker.entities.Habit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class HabitRepository implements CrudRepository<Habit, Long> {
    private final List<Habit> habitContainer;

    @Autowired
    public HabitRepository(List<Habit> habitContainer) {
        this.habitContainer = habitContainer;
    }

    //логика добавления сущности в habitContainer
    @Override
    public void create(Habit habit) {
        habitContainer.add(habit);
    }

    //логика получения сущности по id из habitContainer
    @Override
    public Habit read(Long id) {
        return habitContainer.stream()
                .filter(h -> h.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    //логика обновления сущности в habitContainer
    @Override
    public void update(Habit habit) {
        Optional<Habit> existingHabit = habitContainer.stream()
                .filter(h -> h.getId().equals(habit.getId()))
                .findFirst();

        existingHabit.ifPresent(h -> {
            h.setName(habit.getName());
            h.setDescription(habit.getDescription());
            h.setTargetCount(habit.getTargetCount());
            h.setCurrentCount(habit.getCurrentCount());
            h.setActive(habit.isActive());
        });
    }

    //логика удаления сущности из habitContainer
    @Override
    public void delete(Long id) {
        habitContainer.removeIf(h -> h.getId().equals(id));
    }

    //логика показа всех привычек из habitContainer
    @Override
    public ArrayList<Habit> findAll() {
        return new ArrayList<>(habitContainer);
    }

    //логика увеличения счетчика выполнения привычки из habitContainer
    public void incrementCount(Long id) {
        Optional<Habit> habit = habitContainer.stream()
                .filter(h -> h.getId().equals(id))
                .findFirst();
        habit.ifPresent(Habit::incrementCount);
    }
}