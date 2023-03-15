package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealsStorage {
    Meal create(Meal meal);

    void delete(String uuid);

    Meal update(Meal meal);

    Meal get(String uuid);

    List<Meal> getAll();
}
