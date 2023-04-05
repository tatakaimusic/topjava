package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;

    public static final LocalDateTime INCLUSIVE_DATE_TIME = LocalDateTime.of(2023, 4, 7, 15, 0);

    public static final LocalDateTime DATE_TIME_NEW = LocalDateTime.of(2023, 4, 6, 16, 0);

    public static final LocalDate START_DATE = LocalDate.of(2023, Month.APRIL, 6);

    public static final LocalDate END_DATE = LocalDate.of(2023, Month.APRIL, 8);

    public static final String INCLUSIVE_DESCRIPTION = "user meal 2";

    public static final String NEW_DESCRIPTION = "new meal";

    public static final int CALORIES = 2000;

    public static final Meal INCLUSIVE_MEAL = new Meal(100004, INCLUSIVE_DATE_TIME, INCLUSIVE_DESCRIPTION, CALORIES);

    public static final Meal NEW_MEAL = new Meal(DATE_TIME_NEW, NEW_DESCRIPTION, CALORIES);

    public static final Meal NEW_MEAL_WITH_DUPLICATE_DATE_TIME = new Meal(INCLUSIVE_DATE_TIME, NEW_DESCRIPTION, CALORIES);

    public static final Meal UPDATED_MEAL = new Meal(100003, DATE_TIME_NEW, NEW_DESCRIPTION, CALORIES);

    public static final Meal NOT_FOUND_MEAL = new Meal(1, DATE_TIME_NEW, NEW_DESCRIPTION, CALORIES);

    public static final List<Meal> ALL_MEALS = List.of(
            new Meal(100005, LocalDateTime.of(2023, 4, 9, 20, 0), "user meal 3", 2000),
            new Meal(100004, LocalDateTime.of(2023, 4, 7, 15, 0), "user meal 2", 2000),
            new Meal(100003, LocalDateTime.of(2023, 4, 5, 10, 0), "user meal", 2000)
    );
}
