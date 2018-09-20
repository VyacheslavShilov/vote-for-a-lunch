package ru.slloc.voteforalunch.repository;

import ru.slloc.voteforalunch.model.Dish;

import java.util.List;

public interface DishRepository {

    Dish save (Dish dish, int restaurantId);

    boolean delete(int id, int restaurantId);

    Dish get(int id, int restaurantId);

    // ORDERED name desc
    List<Dish> getAll(int restaurantId);

    List<Dish> getMenu(int restaurantId);
}
