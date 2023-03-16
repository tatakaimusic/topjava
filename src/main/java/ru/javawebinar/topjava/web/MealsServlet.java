package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.storage.MealsStorage;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsServlet extends HttpServlet {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final Logger log = getLogger(MealsServlet.class);

    private static final int CALORIES_PER_DAY = 2000;

    private static MealsStorage storage;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = MealsUtil.STORAGE;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        List<Meal> meals = storage.getAll();
        if (action == null) {
            log.debug("redirect to meals");
            request.setAttribute("meals", MealsUtil.filteredByStreams(meals, CALORIES_PER_DAY));
            request.getRequestDispatcher("meals.jsp").forward(request, response);
            return;
        }
        Meal meal;
        switch (action) {
            case "delete":
                log.debug("delete meal " + id);
                storage.delete(Integer.valueOf(id));
                response.sendRedirect("meals");
                return;
            case "view":
                log.debug("view meal " + id);
                meal = storage.get(Integer.valueOf(id));
                break;
            case "update":
                log.debug("update meal " + id);
                meal = storage.get(Integer.valueOf(id));
                break;
            case "save":
                log.debug("create meal");
                meal = new Meal();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }
        request.setAttribute("meal", meal);
        boolean exist = meal.getId() != null;
        request.setAttribute("exist", exist);
        request.getRequestDispatcher("view".equals(action) ? "view.jsp" : "update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String stringId = request.getParameter("id");
        String date = request.getParameter("date");
        String description = request.getParameter("description");
        String calories = request.getParameter("calories");
        boolean exist = !Objects.equals(stringId, "");
        int id;
        if (!exist) {
            id = storage.size();
        } else {
            id = Integer.parseInt(stringId);
        }
        Meal meal = new Meal(id, LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME), description, Integer.parseInt(calories));
        if (exist) {
            storage.update(meal);
        } else {
            storage.create(meal);
        }
        response.sendRedirect("meals");
    }
}
