package ru.slloc.voteforalunch.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.slloc.voteforalunch.model.Dish;
import ru.slloc.voteforalunch.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(ProfileRestRestaurantController.REST_URL)
public class ProfileRestRestaurantController extends AbstractRestaurantController{
    static final String REST_URL = "/profile/restaurants";

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/get_winner", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getWinner(@RequestParam(value = "date", required = false) LocalDate date) {
        return super.getWinner(date);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @GetMapping(value = "/{id}/menu", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getMenu(@PathVariable("id") int id) {
        return super.getMenu(id);
    }


}
