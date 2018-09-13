package ru.slloc.voteforalunch.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import ru.slloc.voteforalunch.service.RestaurantService;
import ru.slloc.voteforalunch.web.AbstractControllerTest;

public class AbstractControllerRestaurantTest extends AbstractControllerTest {

    @Autowired
    protected RestaurantService restaurantService;

}
