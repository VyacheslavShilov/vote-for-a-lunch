package ru.slloc.voteforalunch.service;

import ru.slloc.voteforalunch.model.Dish;
import ru.slloc.voteforalunch.util.exception.NotFoundException;

import java.util.List;

public interface DishService {

    Dish get(int id, int restaurantId) throws NotFoundException;

    void delete(int id, int restaurantId) throws NotFoundException;

    List<Dish> getAll(int restaurantId);

    List<Dish> getMenu(int restaurantId);

    Dish update(Dish dish, int restaurantId) throws NotFoundException;

    Dish create(Dish dish, int restaurantId);

}
