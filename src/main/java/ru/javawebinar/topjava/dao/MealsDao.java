package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MealsDao {
    private Connection connection;

    public MealsDao() {
        connection = DbUtil.getConnection();
    }

    public void addUser(Meal meal) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into meals(dateTime,description,calories) values (?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setDate(1, Date.valueOf(String.valueOf(meal.getDateTime())));
            preparedStatement.setString(2, meal.getDescription());
            preparedStatement.setInt(3, meal.getCalories());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int mealId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from meals where mealId=?");
            // Parameters start with 1
            preparedStatement.setInt(1, mealId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(Meal meal) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update meals set dateTime=?, desription=?, calories=?" +
                            "where mealIid=?");
            // Parameters start with 1
            preparedStatement.setDate(1, Date.valueOf(String.valueOf(meal.getDateTime())));
            preparedStatement.setString(2, meal.getDescription());
            preparedStatement.setInt(3, meal.getCalories());
            preparedStatement.setInt(4, meal.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Meal> getAllMeals() {
        List<Meal> meals = new ArrayList<Meal>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from meals");
            while (rs.next()) {
                Meal meal = new Meal();
                meal.setId(rs.getInt("Id"));
                meal.setDescription(rs.getString("description"));
                meal.setCalories(rs.getInt("calories"));
                meals.add(meal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return meals;
    }

    public Meal getMealById(int mealId) {
        Meal meal = new Meal();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from meals where mealId=?");
            preparedStatement.setInt(1, mealId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                meal.setId(rs.getInt("Id"));
                meal.setDescription(rs.getString("description"));
                meal.setCalories(rs.getInt("calories"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return meal;
    }
}
