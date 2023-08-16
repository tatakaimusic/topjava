package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Controller
@RequestMapping("/meals")
public class JspMealController {
    private static final Logger log = LoggerFactory.getLogger(JspMealController.class);

    private MealRestController controller;

    @Autowired
    public JspMealController(MealRestController controller) {
        this.controller = controller;
    }

    @GetMapping()
    public String getMeals(Model model) {
        log.info("get meals");
        model.addAttribute("meals", controller.getAll());
        return "meals";
    }

    @GetMapping("/delete?id={id}")
    public String delete(@PathVariable("id") int id) {
        log.info("delete meal with id={}", id);
        controller.delete(id);
        return "redirect:/meals";
    }

    @GetMapping("/update?id={id}")
    public String update(@PathVariable("id") int id, Model model) {
        log.info("get meal form for updating meal with id={}", id);
        model.addAttribute("meal", controller.get(id));
        model.addAttribute("action", "update");
        return "mealForm";
    }

    @GetMapping("/create")
    public String create(Model model) {
        log.info("get meal form for creating");
        model.addAttribute("meal", new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000));
        model.addAttribute("action", "create");
        return "mealForm";
    }

    @PostMapping("/save")
    public String save(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        String id = request.getParameter("id");
        if (StringUtils.hasLength(id)) {
            log.info("update meal with id={}", id);
            meal.setId(Integer.parseInt(id));
            controller.update(meal, Integer.parseInt(id));
        } else {
            log.info("create new meal");
            controller.create(meal);
        }
        return "redirect:/meals";
    }
}
