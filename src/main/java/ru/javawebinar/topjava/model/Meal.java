package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class Meal {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final String uuid;
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    public static Meal EMPTY = new Meal("", LocalDateTime.now(), "", 0);

    public Meal(LocalDateTime dateTime, String description, int calories) {
        Objects.requireNonNull(dateTime, "date time must not be null");
        this.uuid = UUID.randomUUID().toString();
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public Meal(String uuid, LocalDateTime dateTime, String description, int calories) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(dateTime, "date time must not be null");
        this.uuid = uuid;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public String getUuid() {
        return uuid;
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
