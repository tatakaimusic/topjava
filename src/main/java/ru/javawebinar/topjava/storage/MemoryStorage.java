package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MemoryStorage implements MealsStorage {
    private final ConcurrentMap<Integer, Meal> storage = new ConcurrentHashMap<>();

    private int counter = 0;

    public MemoryStorage() {
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    public Meal create(Meal meal) {
        counter += 1;
        meal.setId(counter);
        storage.put(meal.getId(), meal);
        return meal;
    }

    public void delete(Integer id) {
        storage.remove(id);
    }

    public Meal update(Meal meal) {
        storage.put(meal.getId(), meal);
        return meal;
    }

    public Meal get(Integer id) {
        return storage.get(id);
    }

    public List<Meal> getAll() {
        return new ArrayList<>(storage.values());
    }
}
