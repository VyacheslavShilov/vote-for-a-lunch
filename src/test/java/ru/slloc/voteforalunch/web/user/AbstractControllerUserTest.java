package ru.slloc.voteforalunch.web.user;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.slloc.voteforalunch.service.UserService;
import ru.slloc.voteforalunch.web.AbstractControllerTest;
import ru.slloc.voteforalunch.repository.JpaUtil;

public class AbstractControllerUserTest extends AbstractControllerTest {

    @Autowired
    protected UserService userService;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private JpaUtil jpaUtil;

    @BeforeEach
    void setUp() {
        cacheManager.getCache("users").clear();
        if (jpaUtil != null) {
            jpaUtil.clear2ndLevelHibernateCache();
        }
    }
}
