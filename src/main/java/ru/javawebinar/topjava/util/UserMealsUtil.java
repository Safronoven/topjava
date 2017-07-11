package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);


    }

    public static int sumCaloriesPerDay(List<UserMeal> mealList, LocalDate date) {
        return mealList
                .stream()
                .filter(q -> q.getDateTime().toLocalDate().getDayOfMonth() == date.getDayOfMonth())
                .mapToInt(UserMeal::getCalories).sum();

    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMeal> list = mealList
                .stream()
                .filter(userMeal -> TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime))
                .collect(Collectors.toList());

        return list
                .stream()
                .map(q -> {
                    if (sumCaloriesPerDay(mealList, q.getDateTime().toLocalDate()) > caloriesPerDay)
                        return new UserMealWithExceed(q.getDateTime(), q.getDescription(), q.getCalories(), true);
                    else
                        return new UserMealWithExceed(q.getDateTime(), q.getDescription(), q.getCalories(), false);
                })
                .collect(Collectors.toList());
    }
}
