package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class MealTo {
    private final String uuid;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean excess;

    public MealTo(String uuid, LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.uuid = uuid;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;

    }

    @Override
    public String toString() {
        return "MealTo{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExcess() {
        return excess;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getUuid() {
        return uuid;
    }
}
