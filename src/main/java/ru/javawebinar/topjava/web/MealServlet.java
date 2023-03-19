package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.storage.MealsStorage;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MemoryMealStorage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final Logger log = getLogger(MealServlet.class);

    private static final int CALORIES_PER_DAY = 2000;

    private MealsStorage storage;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = new MemoryMealStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            log.debug("redirect to meals");
            request.setAttribute("meals", MealsUtil.filteredByStreams(storage.getAll(), CALORIES_PER_DAY));
            request.setAttribute("FORMATTER", FORMATTER);
            request.getRequestDispatcher("meals.jsp").forward(request, response);
            return;
        }
        Meal meal;
        String id = request.getParameter("id");
        switch (action) {
            case "delete":
                log.debug("delete meal with id {}", id);
                storage.delete(Integer.valueOf(id));
                response.sendRedirect("meals");
                return;
            case "view":
                log.debug("view meal with id {}", id);
                meal = storage.get(Integer.valueOf(id));
                break;
            case "update":
                log.debug("update meal with id {}", id);
                meal = storage.get(Integer.valueOf(id));
                break;
            case "save":
                log.debug("create meal");
                meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
                break;
            default:
                response.sendRedirect("meals");
                return;
        }
        request.setAttribute("meal", meal);
        request.setAttribute("FORMATTER", FORMATTER);
        request.getRequestDispatcher("view".equals(action) ? "mealView.jsp" : "mealUpdate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        LocalDateTime date = LocalDateTime.parse(request.getParameter("date"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        boolean exist = !id.isEmpty();
        Meal meal = new Meal(date, description, calories);
        if (exist) {
            meal.setId(Integer.parseInt(id));
            storage.update(meal);
            log.debug("update meal with id {} completed", id);
        } else {
            storage.create(meal);
            log.debug("create meal with id {} completed", meal.getId());
        }
        response.sendRedirect("meals");
    }
}
