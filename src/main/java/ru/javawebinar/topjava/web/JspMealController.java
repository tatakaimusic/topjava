package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.meal.MealRestController;


@Controller
@RequestMapping(value = "/meals")
public class JspMealController {
    private static final Logger log = LoggerFactory.getLogger(JspMealController.class);

    private final MealService service;

    private final MealRestController controller;

    @Autowired
    public JspMealController(MealService service, MealRestController controller) {
        this.service = service;
        this.controller = controller;
    }

    @GetMapping()
    public String getMeals(Model model) {
        log.info("meals");
        model.addAttribute("meals", controller.getAll());
        return "meals";
    }

    @GetMapping("/delete?id={id}")
    public String delete(@PathVariable("id") int id) {
        log.info("delete meal with id={}", id);
        service.delete(id, SecurityUtil.authUserId());
        return "redirect:/meals";
    }

    @GetMapping("/update?id={id}")
    public String update(@PathVariable("id") int id, Model model) {
        log.info("update meal with id={}", id);
        Meal meal = service.get(id, SecurityUtil.authUserId());
        model.addAttribute("meal", meal);
        return "mealForm";
    }


}
