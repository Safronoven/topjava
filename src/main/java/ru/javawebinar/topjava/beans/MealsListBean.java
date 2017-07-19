package ru.javawebinar.topjava.beans;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class MealsListBean {

    private static List<Meal> meals = Arrays.asList(

    );

    public static List<Meal> getMeals() {
        return meals;
    }
}
