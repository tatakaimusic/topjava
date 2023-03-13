package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.storage.ListStorage;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    private static final int CALORIES_PER_DAY = 2000;

    private static ListStorage storage;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = MealsUtil.STORAGE;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            log.debug("redirect to meals");
            request.setAttribute("meals", MealsUtil.filteredByStreams(storage.getAll(), CALORIES_PER_DAY));
            request.setAttribute("size", storage.size());
            request.getRequestDispatcher("WEB-INF/jsp/meals.jsp").forward(request, response);
            return;
        }
        Meal meal;
        switch (action) {
            case "delete":
                log.debug("delete meal " + uuid);
                storage.delete(uuid);
                response.sendRedirect("meals");
                return;
            case "clear":
                storage.clear();
                response.sendRedirect("meals");
                return;
            case "view":
                log.debug("view meal " + uuid);
            case "update":
                log.debug("update meal " + uuid);
                meal = storage.get(uuid);
                break;
            case "save":
                log.debug("save meal");
                meal = Meal.EMPTY;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("view".equals(action) ? "WEB-INF/jsp/view.jsp" : "WEB-INF/jsp/update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String date = request.getParameter("date");
        String description = request.getParameter("description");
        String calories = request.getParameter("calories");
        boolean exist = true;
        Meal meal;
        if (uuid == null || uuid.length() == 0) {
            exist = false;
            uuid = UUID.randomUUID().toString();
        }
        meal = new Meal(uuid, LocalDateTime.parse(date, Meal.formatter), description, Integer.parseInt(calories));
        if (exist) {
            storage.update(meal);
        } else {
            storage.save(meal);
        }
        response.sendRedirect("meals");
    }
}
