package ru.slloc.voteforalunch.web.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.slloc.voteforalunch.model.Dish;
import ru.slloc.voteforalunch.service.DishService;
import ru.slloc.voteforalunch.service.RestaurantService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(AdminRestDishController.REST_URL)
public class AdminRestDishController  {

    static final String REST_URL = "/admin/restaurants";

   /* @Autowired
    private RestaurantService restaurantService;*/

    @Autowired
    private DishService dishService;

    @GetMapping(value = "/{id}/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAll(@PathVariable("id") int restaurantId) {
        return dishService.getAll(restaurantId);
    }

    @GetMapping(value = "/{id}/dishes/menu", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getMenu(@PathVariable("id") int restaurantId) {
        return dishService.getMenu(restaurantId);
    }

    @GetMapping(value = "/{id}/dishes/{dish_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish get( @PathVariable("dish_id") int id, @PathVariable("id") int restaurantId) {
        return dishService.get(id ,restaurantId);
    }

    @DeleteMapping("/{id}/dishes/{dish_id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("dish_id") int id, @PathVariable("id") int restaurantId) {
        dishService.delete(id, restaurantId);
    }


    @PutMapping(value = "/{id}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Dish dish, @PathVariable("id") int restaurantId) {
        dishService.update(dish, restaurantId);
    }

    @PostMapping(value = "/{id}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish, @PathVariable("id") int restaurantId) {
        Dish created = dishService.create(dish, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
