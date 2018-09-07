package ru.slloc.voteforalunch.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.slloc.voteforalunch.model.Restaurant;

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
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("id") int id) {
        return super.get(id);
    }
}
