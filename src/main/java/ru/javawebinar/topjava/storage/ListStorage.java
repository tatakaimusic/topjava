package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListStorage implements Storage {
    private List<Meal> storage = new ArrayList<>();

    public ListStorage() {
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

    public void save(Meal meal) {
        storage.add(meal);
    }

    public void delete(String uuid) {
        storage.remove(getIndex(uuid).intValue());
    }

    public void update(Meal meal) {
        storage.set(getIndex(meal.getUuid()).intValue(), meal);
    }

    public Meal get(String uuid) {
        return storage.get(getIndex(uuid).intValue());
    }

    public List<Meal> getAll() {
        return storage;
    }

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }
}
