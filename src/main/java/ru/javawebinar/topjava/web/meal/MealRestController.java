package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;


import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.*;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final int userId = SecurityUtil.authUserId();

    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal create(Meal meal) {
        log.info("create {} with userId={}", meal, userId);
        checkNew(meal);
        return service.create(meal, userId);
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={} where userId={}", meal, id, userId);
        assureIdConsistent(meal, id);
        service.update(meal, userId);
    }

    public void delete(int id) {
        log.info("delete {} where userId={}", id, userId);
        checkNotFound(service.get(id, userId), "Meal not found");
        service.delete(id, userId);
    }

    public Meal get(int id) {
        log.info("get {} where userId={}", id, userId);
        return checkNotFound(service.get(id, userId), "Meal not found");
    }

    public List<MealTo> getAll() {
        log.info("get all where userId={}", userId);
        return MealsUtil.getTos(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay());
    }
}