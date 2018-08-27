package ru.slloc.voteforalunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.slloc.voteforalunch.UserTestData;
import ru.slloc.voteforalunch.model.Restaurant;
import ru.slloc.voteforalunch.util.exception.NotFoundException;

import java.util.List;

import static ru.slloc.voteforalunch.RestaurantTestData.*;
import static ru.slloc.voteforalunch.UserTestData.ADMIN;
import static ru.slloc.voteforalunch.UserTestData.USER2;
import static ru.slloc.voteforalunch.UserTestData.USER_ID;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void create() throws Exception{
        Restaurant newRestaurant = new Restaurant(null, "NewRest", false);
        Restaurant created = service.create(newRestaurant);
        newRestaurant.setId(created.getId());
        assertMatch(service.getAll(), newRestaurant, RESTAURANT2, RESTAURANT1 );
    }

    @Test
    public void delete() throws Exception {
        service.delete(RESTAURANT2_ID);
        assertMatch(service.getAll(), RESTAURANT1);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateNameCreate() throws Exception{
        service.create(new Restaurant(null, RESTAURANT2.getName()));
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void get() throws Exception {
        Restaurant restaurant = service.get(RESTAURANT1_ID);
        assertMatch(restaurant, RESTAURANT1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void getByName() throws Exception {
        Restaurant restaurant = service.getByName("Заря");
        assertMatch(restaurant, RESTAURANT2);
    }

    @Test
    public void update() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT1);
        updated.setName("UpdatedName");
        service.update(updated);
        assertMatch(service.get(RESTAURANT1_ID), updated);
    }

    @Test
    public void getAll() throws Exception {
        List<Restaurant> all = service.getAll();
        assertMatch(all, RESTAURANT2, RESTAURANT1);
    }
}
