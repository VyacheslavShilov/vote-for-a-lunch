package ru.slloc.voteforalunch.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.slloc.voteforalunch.model.Dish;
import ru.slloc.voteforalunch.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.slloc.voteforalunch.DishTestData.*;
import static ru.slloc.voteforalunch.DishTestData.assertMatch;
import static ru.slloc.voteforalunch.RestaurantTestData.RESTAURANT1_ID;
import static ru.slloc.voteforalunch.RestaurantTestData.RESTAURANT2_ID;

public class DishServiceTest extends AbstractServiceTest{

    @Autowired
    protected DishService service;

    @Test
    public void delete() throws Exception {
        service.delete(DISH1_ID, RESTAURANT1_ID);
        assertMatch(service.getAll(RESTAURANT1_ID), DISH3);
    }

    @Test
    public void deleteNotFound() throws Exception {
        // thrown.expect(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> service.delete(DISH1_ID, 1));
    }

    @Test
    public void create() throws Exception {
        Dish created = getCreated();
        service.create(created, RESTAURANT1_ID);
        assertMatch(service.getAll(RESTAURANT1_ID),created, DISH3, DISH1 );
    }

    @Test
    public void get() throws Exception {
        Dish actual = service.get(DISH2_ID, RESTAURANT2_ID);
        assertMatch(actual, DISH2);
    }

    //DISH3 in RESTAURANT1
    @Test
    public void getNotFound() throws Exception {
        // thrown.expect(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> service.get(DISH3_ID, RESTAURANT2_ID));
    }

    //updated DISH1
    @Test
    public void update() throws Exception{
        Dish updated = getUpdated();
        service.update(updated, RESTAURANT1_ID);
        assertMatch(service.get(DISH1_ID, RESTAURANT1_ID), updated);
    }

    //DISH4 in RESTAURANT2
    @Test
    public void updateNotFound() throws Exception {
        // thrown.expect(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> service.update(DISH4, RESTAURANT1_ID));
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(RESTAURANT2_ID), DISH5, DISH2, DISH4);
    }

    @Test
    public void getMenu() throws Exception{
        assertMatch(service.getMenu(RESTAURANT2_ID), DISH2, DISH4 );
    }

}
