package ru.slloc.voteforalunch.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataAccessException;
import ru.slloc.voteforalunch.VoteTestData;
import ru.slloc.voteforalunch.model.Role;
import ru.slloc.voteforalunch.model.User;
import ru.slloc.voteforalunch.util.exception.NotFoundException;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.slloc.voteforalunch.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest{

    @Autowired
    private UserService service;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    public void setUp() throws Exception {
        cacheManager.getCache("users").clear();
    }

    @Test
    public void create() throws Exception {
        User newUser = new User(null, "New", "new@gmail.com", "newPass",  false, new Date(), Collections.singleton(Role.ROLE_USER));
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(), ADMIN, newUser, USER, USER2);
    }


    @Test
    public void delete() throws Exception {
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN, USER2);
    }

   // @Test(expected = DataAccessException.class)
    @Test
    public void duplicateMailCreate() throws Exception {
       // service.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER));
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER)));
    }

  //  @Test(expected = NotFoundException.class)
  @Test
    public void notFoundDelete() throws Exception {
     //   service.delete(1);
      assertThrows(NotFoundException.class, () ->
              service.delete(1));

    }

    @Test
    public void get() throws Exception {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
    }

    @Test
    public void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.get(1));
    }

    @Test
    public void getByEmail() throws Exception {
        User user = service.getByEmail("user@yandex.ru");
        assertMatch(user, USER);
    }

    @Test
    public void update() throws Exception {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        service.update(updated);
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    public void getAll() throws Exception {
        List<User> all = service.getAll();
        assertMatch(all, ADMIN, USER, USER2);
    }

    @Test
    void getWithVotes() throws Exception{
        User admin = service.getWithVotes(ADMIN_ID);
        assertMatch(admin, ADMIN);
        VoteTestData.assertMatch(admin.getVotes(), VoteTestData.ADMIN_VOTE3, VoteTestData.ADMIN_VOTE2, VoteTestData.ADMIN_VOTE1);
    }

    @Test
    void getWithVotesNotFound() throws Exception{
       assertThrows(NotFoundException.class, () -> service.getWithVotes(1));
    }
}