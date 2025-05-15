package ru.daria.NauJava.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.daria.NauJava.entities.Habit;
import ru.daria.NauJava.service.HabitService;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
@RequiredArgsConstructor
public class HabitController {
    private HabitService habitService;

    @PostMapping
    public Habit createHabit(@RequestBody Habit habit) {
        return habitService.createHabit(habit);
    }

    @PutMapping("/{id}")
    public Habit updateHabit(@PathVariable Long id, @RequestBody Habit habit) {
        habit.setId(id);
        return habitService.updateHabit(habit);
    }

    @DeleteMapping("/{id}")
    public void deleteHabit(@PathVariable Long id) {
        habitService.deleteHabit(id);
    }

    @GetMapping("/user/{userId}")
    public List<Habit> getUserHabits(@PathVariable Long userId) {
        return habitService.getAllUserHabits(userId);
    }

    @GetMapping("/user/{userId}/active")
    public List<Habit> getActiveHabits(@PathVariable Long userId) {
        return habitService.getActiveHabits(userId);
    }

    @PostMapping("/{id}/increment")
    public Habit incrementHabitCount(@PathVariable Long id) {
        return habitService.incrementHabitCount(id);
    }
}