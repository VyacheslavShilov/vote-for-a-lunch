package ru.slloc.voteforalunch.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.slloc.voteforalunch.model.Dish;
import ru.slloc.voteforalunch.model.Restaurant;
import ru.slloc.voteforalunch.service.DishService;
import ru.slloc.voteforalunch.service.RestaurantService;

import java.time.LocalDate;
import java.util.List;

import static ru.slloc.voteforalunch.util.ValidationUtil.assureIdConsistent;
import static ru.slloc.voteforalunch.util.ValidationUtil.checkNew;

public class AbstractRestaurantController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    public List<Restaurant> getAll() {
        log.info("getAll");
        return restaurantService.getAll();
    }

    public List<Restaurant> getWinner(LocalDate date) {
        log.info("getWinner");
        if (date != null) return restaurantService.getWinner(date);
        else return restaurantService.getWinner(LocalDate.now());
    }

    public Restaurant get(int id) {
        log.info("get {}", id);
        return restaurantService.get(id);
    }

    public Restaurant getByName(String name) {
        log.info("getByName {}", name);
        return restaurantService.getByName(name);
    }

    public List<Dish> getMenu(int id){
        log.info("getMenu {}", id);
        return dishService.getMenu(id);
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        return restaurantService.create(restaurant);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        restaurantService.delete(id);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update {} with id={}", restaurant, id);
        assureIdConsistent(restaurant, id);
        restaurantService.update(restaurant);
    }




}
