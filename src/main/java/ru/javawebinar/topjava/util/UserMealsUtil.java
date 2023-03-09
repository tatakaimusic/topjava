package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);


        List<UserMealWithExcess> mealsWithExcess = filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsWithExcess.forEach(System.out::println);
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> result = new ArrayList<>();
        Map<Integer, Integer> dateCol = new LinkedHashMap<>();
        Map<Integer, Boolean> dateExc = new LinkedHashMap<>();
        for (UserMeal meal : meals) {
            int day = meal.getDateTime().getDayOfMonth();
            int cal = meal.getCalories();
            if (dateCol.getOrDefault(day, null) == null) {
                dateCol.put(day, cal);
            } else {
                dateCol.merge(day, dateCol.get(day), (a, b) -> b + cal);
            }
        }
        for (Map.Entry<Integer, Integer> day : dateCol.entrySet()) {
            if (day.getValue() > caloriesPerDay) {
                dateExc.put(day.getKey(), true);
            } else {
                dateExc.put(day.getKey(), false);
            }
        }
        for (UserMeal meal : meals) {
            if (TimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                result.add(new UserMealWithExcess(meal, dateExc.get(meal.getDateTime().getDayOfMonth())));
            }
        }
        return result;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> result = new ArrayList<>();
        Map<Integer, Integer> dateCol = new LinkedHashMap<>();
        Map<Integer, Boolean> dateExc = new LinkedHashMap<>();
        meals.stream().map(meal -> (dateCol.getOrDefault(meal.getDateTime().getDayOfMonth(), null)) == null
                ? dateCol.put(meal.getDateTime().getDayOfMonth(), meal.getCalories())
                : dateCol.merge(meal.getDateTime().getDayOfMonth(), dateCol.get(meal.getDateTime().getDayOfMonth()), (a, b) -> dateCol.get(meal.getDateTime().getDayOfMonth()) + meal.getCalories()));
        dateCol.entrySet().stream().map(day -> (day.getValue() > caloriesPerDay) ? dateExc.put(day.getKey(), true) : dateExc.put(day.getKey(), false));
        meals.stream().map(meal -> (TimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime, endTime))
                ? result.add(new UserMealWithExcess(meal, dateExc.get(meal.getDateTime().getDayOfMonth())))
                : null);
        return result;
    }
}




