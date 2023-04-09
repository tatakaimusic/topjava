package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;


import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-app-jdbc.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService mealService;

    @Test
    public void get() {
        Meal meal = mealService.get(userMeal2.getId(), USER_ID);
        MealTestData.assertMatch(meal, userMeal2);
    }

    @Test
    public void getAlienMeal() {
        Assert.assertThrows(NotFoundException.class, () -> mealService.get(adminMeal.getId(), USER_ID));
    }

    @Test
    public void getNotFound() {
        Assert.assertThrows(NotFoundException.class, () -> mealService.get(notFoundMeal.getId(), USER_ID));
    }

    @Test
    public void delete() {
        mealService.delete(userMeal2.getId(), USER_ID);
        Assert.assertThrows(NotFoundException.class, () -> mealService.get(userMeal2.getId(), USER_ID));
    }

    @Test
    public void deleteNotFound() {
        Assert.assertThrows(NotFoundException.class, () -> mealService.delete(notFoundMeal.getId(), USER_ID));
    }

    @Test
    public void deleteAlienMeal() {
        Assert.assertThrows(NotFoundException.class, () -> mealService.delete(adminMeal.getId(), USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> meals = mealService.getBetweenInclusive(START_DATE, END_DATE, USER_ID);
        assertMatch(meals, filteredUserMeals);
    }

    @Test
    public void getBetweenInclusiveNull() {
        List<Meal> meals = mealService.getBetweenInclusive(null, null, USER_ID);
        assertMatch(meals, allUserMeals);
    }

    @Test
    public void getAll() {
        List<Meal> meals = mealService.getAll(USER_ID);
        assertMatch(meals, allUserMeals);
    }

    @Test
    public void update() {
        Meal updated = MealTestData.getUpdated();
        mealService.update(updated, USER_ID);
        assertMatch(mealService.get(updated.getId(), USER_ID), MealTestData.getUpdated());
    }

    @Test
    public void updateNotExist() {
        Assert.assertThrows(NotFoundException.class, () -> mealService.update(MealTestData.getNotFound(), USER_ID));
    }

    @Test
    public void updateAlienMeal() {
        Assert.assertThrows(NotFoundException.class, () -> mealService.update(MealTestData.getAlienMeal(), USER_ID));
    }

    @Test
    public void create() {
        Meal created = mealService.create(getNew(), USER_ID);
        Meal newMeal = getNew();
        Integer newId = created.getId();
        newMeal.setId(newId);
        assertMatch(mealService.get(newId, USER_ID), newMeal);
        assertMatch(created, newMeal);
    }

    @Test
    public void duplicateDateTimeCreate() {
        Meal duplicate = newMealWithDuplicateDateTime;
        Assert.assertThrows(DataAccessException.class, () -> mealService.create(duplicate, USER_ID));
    }
}