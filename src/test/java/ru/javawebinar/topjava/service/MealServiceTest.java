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
        Meal meal = mealService.get(USER_MEAL_2.getId(), USER_ID);
        Assert.assertEquals(USER_MEAL_2.toString(), meal.toString());
    }

    @Test
    public void getAlienMeal() {
        Assert.assertThrows(NotFoundException.class, () -> mealService.get(ADMIN_MEAL.getId(), USER_ID));
    }

    @Test
    public void getNotFound() {
        Assert.assertThrows(NotFoundException.class, () -> mealService.get(NOT_FOUND_MEAL.getId(), USER_ID));
    }

    @Test
    public void delete() {
        mealService.delete(100004, USER_ID);
        Assert.assertThrows(NotFoundException.class, () -> mealService.get(USER_MEAL_2.getId(), USER_ID));
    }

    @Test
    public void deleteNotFound() {
        Assert.assertThrows(NotFoundException.class, () -> mealService.delete(NOT_FOUND_MEAL.getId(), USER_ID));
    }

    @Test
    public void deleteAlienMeal() {
        Assert.assertThrows(NotFoundException.class, () -> mealService.delete(ADMIN_MEAL.getId(), USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> meals = mealService.getBetweenInclusive(START_DATE, END_DATE, USER_ID);
        Assert.assertEquals(INCLUSIVE_MEALS, meals);
    }

    @Test
    public void getBetweenInclusiveNull() {
        List<Meal> meals = mealService.getBetweenInclusive(null, null, USER_ID);
        Assert.assertEquals(ALL_MEALS, meals);
    }

    @Test
    public void getAll() {
        List<Meal> meals = mealService.getAll(USER_ID);
        Assert.assertEquals(ALL_MEALS, meals);
    }

    @Test
    public void update() {
        Meal updated = MealTestData.getUpdatedMeal();
        mealService.update(updated, USER_ID);
        Assert.assertEquals(updated.toString(), mealService.get(updated.getId(), USER_ID).toString());
    }

    @Test
    public void updateNotExist() {
        Assert.assertThrows(NotFoundException.class, () -> mealService.update(MealTestData.getNotFoundMeal(), USER_ID));
    }

    @Test
    public void updateAlienMeal() {
        Assert.assertThrows(NotFoundException.class, () -> mealService.update(MealTestData.getAdminMeal(), USER_ID));
    }

    @Test
    public void create() {
        Meal created = mealService.create(NEW_MEAL, USER_ID);
        Integer newId = created.getId();
        Assert.assertEquals(NEW_MEAL.toString(), created.toString());
        Assert.assertEquals(NEW_MEAL.toString(), mealService.get(newId, USER_ID).toString());
    }

    @Test
    public void duplicateDateTimeCreate() {
        Assert.assertThrows(DataAccessException.class, () -> mealService.create(NEW_MEAL_WITH_DUPLICATE_DATE_TIME, USER_ID));
    }
}