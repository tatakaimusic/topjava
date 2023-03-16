package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealsStorage {
    Meal create(Meal meal);

    void delete(Integer id);

    Meal update(Meal meal);

    Meal get(Integer id);

    List<Meal> getAll();
}
