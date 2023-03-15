package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class MemoryStorage implements MealsStorage {
    private final Map<String, Meal> storage = Collections.synchronizedMap(new LinkedHashMap<>());

    public MemoryStorage() {
//        storage.addAll(Arrays.asList(
//                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
//                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
//                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
//                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
//                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
//                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
//                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
//        ));
        Meal meal = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
        storage.put(meal.getUuid(), meal);
    }

    public Meal create(Meal meal) {
        storage.put(meal.getUuid(), meal);
        return meal;
    }

    public void delete(String uuid) {
        storage.remove(uuid);
    }

    public Meal update(Meal meal) {
        storage.put(meal.getUuid(), meal);
        return meal;
    }

    public Meal get(String uuid) {
        return storage.get(uuid);
    }

    public List<Meal> getAll() {
        return new ArrayList<>(storage.values());
    }
}
