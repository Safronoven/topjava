package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.beans.MealsListBean;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug("redirect to meals");

        Map<LocalDate, Integer> caloriesSumByDate = MealsListBean.getMeals()
                .stream()
                .collect(Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum));

        List<MealWithExceed> mealList = MealsListBean.getMeals()
                .stream()
                .map(meal -> MealsUtil.createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > MealsListBean.getCaloriesPerDay()))
                .collect(Collectors.toList());

        for (MealWithExceed q : mealList) {
            request.setAttribute("date", q.getDate());
            request.setAttribute("time", q.getTime());
            request.setAttribute("description", q.getDescription());
            request.setAttribute("calories", q.getCalories());
            request.setAttribute("exceed", q.isExceed());
        }

        request.setAttribute("mealList", mealList);
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }
}
