package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class MemoryStorage implements MealsStorage {
    private List<Meal> storage = new ArrayList<>();

    public MemoryStorage() {
        storage.addAll(Arrays.asList(
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        ));
    }

    private Integer getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    public Meal create(Meal meal) {
        storage.add(meal);
        return meal;
    }

    public void delete(String uuid) {
        storage.remove(Objects.requireNonNull(getIndex(uuid)).intValue());
    }

    public Meal update(Meal meal) {
        storage.set(Objects.requireNonNull(getIndex(meal.getUuid())), meal);
        return meal;
    }

    public Meal get(String uuid) {
        return storage.get(Objects.requireNonNull(getIndex(uuid)).intValue());
    }

    public List<Meal> getAll() {
        return storage;
    }
}
