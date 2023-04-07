package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    private static final int CALORIES = 2000;
    private static final LocalDateTime USER_MEAL_DATE_TIME = LocalDateTime.of(2023, 4, 5, 10, 0);

    private static final LocalDateTime USER_MEAL_2_DATE_TIME = LocalDateTime.of(2023, 4, 7, 15, 0);

    private static final LocalDateTime USER_MEAL_2_2_DATE_TIME = LocalDateTime.of(2023, 4, 7, 20, 0);

    private static final LocalDateTime USER_MEAL_3_DATE_TIME = LocalDateTime.of(2023, 4, 9, 20, 0);

    private static final LocalDateTime ADMIN_MEAL_DATE_TIME = LocalDateTime.of(2023, 4, 5, 15, 0);

    private static final LocalDateTime NEW_DATE_TIME = LocalDateTime.of(2023, 4, 11, 10, 0);

    public static final Meal USER_MEAL = new Meal(START_SEQ + 3, USER_MEAL_DATE_TIME, "user meal", CALORIES);

    public static final Meal USER_MEAL_2 = new Meal(START_SEQ + 4, USER_MEAL_2_DATE_TIME, "user meal 2", CALORIES);

    public static final Meal USER_MEAL_2_2 = new Meal(START_SEQ + 5, USER_MEAL_2_2_DATE_TIME, "user meal 2.2", 100);

    public static final Meal USER_MEAL_3 = new Meal(START_SEQ + 6, USER_MEAL_3_DATE_TIME, "user meal 3", CALORIES);

    public static final Meal ADMIN_MEAL = new Meal(START_SEQ + 7, ADMIN_MEAL_DATE_TIME, "admin meal", CALORIES);

    public static final List<Meal> ALL_MEALS = List.of(USER_MEAL_3, USER_MEAL_2_2, USER_MEAL_2, USER_MEAL);

    public static final Meal NOT_FOUND_MEAL = new Meal(1, USER_MEAL_DATE_TIME, "user meal", CALORIES);

    public static final Meal NEW_MEAL = new Meal(NEW_DATE_TIME, "user meal", CALORIES);

    public static final Meal UPDATED_MEAL = new Meal(START_SEQ + 6, NEW_DATE_TIME, "user meal", CALORIES);

    public static final Meal NEW_MEAL_WITH_DUPLICATE_DATE_TIME = new Meal(USER_MEAL_2_DATE_TIME, "user meal", CALORIES);

    public static final LocalDate START_DATE = LocalDate.of(2023, 4, 6);

    public static final LocalDate END_DATE = LocalDate.of(2023, 4, 8);

    public static final List<Meal> INCLUSIVE_MEALS = List.of(USER_MEAL_2_2, USER_MEAL_2);

    public static Meal getUpdatedMeal() {
        return UPDATED_MEAL;
    }

    public static Meal getNotFoundMeal() {
        return NOT_FOUND_MEAL;
    }

    public static Meal getAdminMeal() {
        return ADMIN_MEAL;
    }
}
