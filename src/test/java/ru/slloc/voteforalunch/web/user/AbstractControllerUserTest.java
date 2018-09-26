package ru.slloc.voteforalunch.web.user;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.slloc.voteforalunch.service.UserService;
import ru.slloc.voteforalunch.web.AbstractControllerTest;

public class AbstractControllerUserTest extends AbstractControllerTest {

    @Autowired
    protected UserService userService;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    void setUp() {
        cacheManager.getCache("users").clear();
        /*if (jpaUtil != null) {
            jpaUtil.clear2ndLevelHibernateCache();
        }*/
    }
}
