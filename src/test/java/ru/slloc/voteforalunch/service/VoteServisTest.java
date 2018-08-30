package ru.slloc.voteforalunch.service;

import org.junit.Assume;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.slloc.voteforalunch.model.Vote;
import ru.slloc.voteforalunch.util.exception.NotFoundException;

import static ru.slloc.voteforalunch.UserTestData.ADMIN_ID;
import static ru.slloc.voteforalunch.VoteTestData.*;
import static ru.slloc.voteforalunch.UserTestData.USER_ID;
import static ru.slloc.voteforalunch.VoteTestData.VOTE1_ID;

public class VoteServisTest extends AbstractServiceTest {

    @Autowired
    protected VoteService service;

    @Test
    public void delete() throws Exception {
        service.delete(VOTE1_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), VOTE4);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
       // thrown.expect(NotFoundException.class);
        service.delete(VOTE1_ID, 1);
    }

    @Test
    public void create() throws Exception {
        Vote created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), created, VOTE4, VOTE1);
    }

    @Test
    public void get() throws Exception {
        Vote actual = service.get(VOTE3_ID, ADMIN_ID);
        assertMatch(actual, VOTE3);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        // thrown.expect(NotFoundException.class);
        service.delete(VOTE1_ID, ADMIN_ID);
    }

    @Test
    public void update() throws Exception {
        Vote updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(VOTE1_ID, USER_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() throws Exception {
        // thrown.expect(NotFoundException.class);
        service.update(VOTE1, ADMIN_ID);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(USER_ID), VOTE4, VOTE1);
    }
}
