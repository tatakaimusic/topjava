package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryMealStorage implements MealsStorage {
    private final ConcurrentMap<Integer, Meal> storage = new ConcurrentHashMap<>();

    private AtomicInteger counter = new AtomicInteger(0);

    public MemoryMealStorage() {
        List<Meal> meals = Arrays.asList(
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );
        for (Meal meal : meals) {
            create(meal);
        }
    }

    public Meal create(Meal meal) {
        meal.setId(counter.incrementAndGet());
        storage.put(meal.getId(), meal);
        return meal;
    }

    public void delete(int id) {
        storage.remove(id);
    }

    public Meal update(Meal meal) {
        return storage.computeIfPresent(meal.getId(), (k, v) -> meal);
    }

    public Meal get(int id) {
        return storage.get(id);
    }

    public List<Meal> getAll() {
        return new ArrayList<>(storage.values());
    }
}
