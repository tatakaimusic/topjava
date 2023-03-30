package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.*;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal create(Meal meal) {
        log.info("create {} with userId={}", meal, SecurityUtil.getAuthUserId());
        checkNew(meal);
        return service.create(meal, SecurityUtil.getAuthUserId());
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={} where userId={}", meal, id, SecurityUtil.getAuthUserId());
        assureIdConsistent(meal, id);
        service.update(meal, SecurityUtil.getAuthUserId());
    }

    public void delete(int id) {
        log.info("delete {} where userId={}", id, SecurityUtil.getAuthUserId());
        service.delete(id, SecurityUtil.getAuthUserId());
    }

    public Meal get(int id) {
        log.info("get {} where userId={}", id, SecurityUtil.getAuthUserId());
        return service.get(id, SecurityUtil.getAuthUserId());
    }

    public List<MealTo> getAll() {
        log.info("get all where userId={}", SecurityUtil.getAuthUserId());
        return MealsUtil.getTos(service.getAll(SecurityUtil.getAuthUserId()), SecurityUtil.authUserCaloriesPerDay());
    }

    public List<MealTo> getAllFiltered(LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime) {
        log.info("get all filtered where userId={}", SecurityUtil.getAuthUserId());
        fromDate = fromDate == null ? LocalDate.MIN : fromDate;
        toDate = toDate == null ? LocalDate.MAX : toDate;
        fromTime = fromTime == null ? LocalTime.MIN : fromTime;
        toTime = toTime == null ? LocalTime.MAX : toTime;
        return MealsUtil.getFilteredTos(service.getAllFilteredByDate(SecurityUtil.getAuthUserId(), fromDate, toDate), SecurityUtil.authUserCaloriesPerDay(), fromTime, toTime);
    }
}