package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal create(Meal meal, int userId) {
        log.info("create {} with userId={}", meal, userId);
        return service.create(meal, userId);
    }

    public void update(Meal meal, int id, int userId) {
        log.info("update {} with id={} where userId={}", meal, id, userId);
        assureIdConsistent(meal, id);
        service.update(meal, userId);
    }

    public void delete(int id, int userId) {
        log.info("delete {} where userId={}", id, userId);
        service.delete(id, userId);
    }

    public Meal get(int id, int userId) {
        log.info("get {} where userId={}", id, userId);
        return service.get(id, userId);
    }

    public List<Meal> getAll(int userId) {
        log.info("get all where userId={}", userId);
        return service.getAll(userId);
    }
}