package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class MemoryStorage implements MealsStorage {
    private final Map<Integer, Meal> storage = Collections.synchronizedMap(new LinkedHashMap<>());

    public MemoryStorage() {
        Meal meal = new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
        storage.put(meal.getId(), meal);
        meal = new Meal(1, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
        storage.put(meal.getId(), meal);
        meal = new Meal(2, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
        storage.put(meal.getId(), meal);
        meal = new Meal(3, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
        storage.put(meal.getId(), meal);
        meal = new Meal(4, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
        storage.put(meal.getId(), meal);
        meal = new Meal(5, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
        storage.put(meal.getId(), meal);
        meal = new Meal(6, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);
        storage.put(meal.getId(), meal);
    }

    public Meal create(Meal meal) {
        storage.put(storage.size(), meal);
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

    public int size() {
        return storage.size();
    }
}
