package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class Meal {
    private final Integer id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;


    public Meal(LocalDateTime dateTime, String description, int calories) {
        this.id = null;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public Meal() {
        this.id = null;
        this.dateTime = LocalDateTime.now();
        this.description = "";
        this.calories = 0;
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}
