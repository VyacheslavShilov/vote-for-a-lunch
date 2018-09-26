package ru.slloc.voteforalunch.web.restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.slloc.voteforalunch.service.RestaurantService;
import ru.slloc.voteforalunch.web.AbstractControllerTest;

public class AbstractControllerRestaurantTest extends AbstractControllerTest {

    @Autowired
    protected RestaurantService restaurantService;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    void setUp() {
        cacheManager.getCache("restaurants").clear();
        /*if (jpaUtil != null) {
            jpaUtil.clear2ndLevelHibernateCache();
        }*/
    }

}
