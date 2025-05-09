package ru.daria.habitTracker.entities;

public class Habit {
    private Long id;
    private String name;
    private String description;
    private int targetCount;
    private int currentCount;
    private boolean isActive;

    public Habit() {
    }

    public Habit(Long id, String name, String description, int targetCount,
                 int currentCount, boolean isActive) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.targetCount = targetCount;
        this.currentCount = currentCount;
        this.isActive = isActive;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTargetCount() {
        return targetCount;
    }

    public void setTargetCount(int targetCount) {
        this.targetCount = targetCount;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void incrementCount() {
        if (currentCount < targetCount) {
            currentCount++;
        }
    }

    public boolean isCompleted() {
        return currentCount >= targetCount;
    }

    @Override
    public String toString() {
        return "Habit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", targetCount=" + targetCount +
                ", currentCount=" + currentCount +
                ", isActive=" + isActive +
                '}';
    }
}