package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface Storage {
    void save(Meal meal);

    void delete(String uuid);

    void update(Meal meal);

    Meal get(String uuid);

    List<Meal> getAll();

    void clear();

    int size();
}
