package ru.slloc.voteforalunch.service;

import ru.slloc.voteforalunch.model.Restaurant;
import ru.slloc.voteforalunch.model.User;
import ru.slloc.voteforalunch.util.exception.NotFoundException;

import java.util.List;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    void delete(int id) throws NotFoundException;

    Restaurant get(int id) throws NotFoundException;

    Restaurant getByName(String name) throws NotFoundException;

    void update(Restaurant restaurant);

    List<Restaurant> getAll();
}
