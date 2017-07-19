package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealsDao;
import ru.javawebinar.topjava.model.Meal;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MealServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/meals.jsp";
    private static String LIST_MEAL = "/listMeal.jsp";
    private MealsDao dao;

    public MealServlet() {
        super();
        dao = new MealsDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int mealId = Integer.parseInt(request.getParameter("Id"));
            dao.deleteUser(mealId);
            forward = LIST_MEAL;
            request.setAttribute("meals", dao.getAllMeals());
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(request.getParameter("Id"));
            Meal meal = dao.getMealById(mealId);
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("listMeal")){
            forward = LIST_MEAL;
            request.setAttribute("meals", dao.getAllMeals());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Meal meal = new Meal();
        meal.setDescription(request.getParameter("description"));
        meal.setCalories(Integer.parseInt(request.getParameter("calories")));
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("date"));
        meal.setDateTime(dateTime);
        String mealId = request.getParameter("Id");
        if(mealId == null || mealId.isEmpty())
        {
            dao.addUser(meal);
        }
        else
        {
            meal.setId(Integer.parseInt(mealId));
            dao.updateUser(meal);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_MEAL);
        request.setAttribute("meals", dao.getAllMeals());
        view.forward(request, response);
    }
}