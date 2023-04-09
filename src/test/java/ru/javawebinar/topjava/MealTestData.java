package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    private static final int CALORIES = 2000;
    private static final LocalDateTime USER_MEAL_DATE_TIME = LocalDateTime.of(2023, 4, 5, 10, 0);

    private static final LocalDateTime USER_MEAL_2_DATE_TIME = LocalDateTime.of(2023, 4, 7, 15, 0);

    private static final LocalDateTime USER_MEAL_2_2_DATE_TIME = LocalDateTime.of(2023, 4, 7, 20, 0);

    private static final LocalDateTime USER_MEAL_3_DATE_TIME = LocalDateTime.of(2023, 4, 9, 20, 0);

    private static final LocalDateTime ADMIN_MEAL_DATE_TIME = LocalDateTime.of(2023, 4, 5, 15, 0);

    private static final LocalDateTime NEW_DATE_TIME = LocalDateTime.of(2023, 4, 11, 10, 0);

    public static Meal userMeal = new Meal(START_SEQ + 3, USER_MEAL_DATE_TIME, "user meal", CALORIES);

    public static Meal userMeal2 = new Meal(START_SEQ + 4, USER_MEAL_2_DATE_TIME, "user meal 2", CALORIES);

    public static Meal userMeal22 = new Meal(START_SEQ + 5, USER_MEAL_2_2_DATE_TIME, "user meal 2.2", 100);

    public static Meal userMeal3 = new Meal(START_SEQ + 6, USER_MEAL_3_DATE_TIME, "user meal 3", CALORIES);

    public static Meal adminMeal = new Meal(START_SEQ + 7, ADMIN_MEAL_DATE_TIME, "admin meal", CALORIES);

    public static List<Meal> allUserMeals = List.of(userMeal3, userMeal22, userMeal2, userMeal);

    public static Meal notFoundMeal = new Meal(1, USER_MEAL_DATE_TIME, "user meal", CALORIES);

    public static Meal newMeal = new Meal(NEW_DATE_TIME, "user meal", CALORIES);

    public static Meal updatedMeal = new Meal(START_SEQ + 6, NEW_DATE_TIME, "user meal", CALORIES);

    public static Meal newMealWithDuplicateDateTime = new Meal(USER_MEAL_2_DATE_TIME, "user meal", CALORIES);

    public static final LocalDate START_DATE = LocalDate.of(2023, 4, 6);

    public static final LocalDate END_DATE = LocalDate.of(2023, 4, 8);

    public static List<Meal> filteredUserMeals = List.of(userMeal22, userMeal2);

    public static Meal getUpdated() {
        return new Meal(updatedMeal);
    }

    public static Meal getNotFound() {
        return new Meal(notFoundMeal);
    }

    public static Meal getAlienMeal() {
        return new Meal(adminMeal);
    }

    public static Meal getNew() {
        return new Meal(newMeal);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }
}
