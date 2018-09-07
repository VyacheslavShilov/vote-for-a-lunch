package ru.slloc.voteforalunch.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import ru.slloc.voteforalunch.service.UserService;
import ru.slloc.voteforalunch.web.AbstractControllerTest;

public class AbstractControllerUserTest extends AbstractControllerTest {

    @Autowired
    protected UserService userService;
}
