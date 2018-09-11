package ru.slloc.voteforalunch.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.slloc.voteforalunch.model.Vote;
import ru.slloc.voteforalunch.util.exception.NotFoundException;
import ru.slloc.voteforalunch.util.exception.TimeForVoteIsFinishedException;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static ru.slloc.voteforalunch.UserTestData.ADMIN_ID;
import static ru.slloc.voteforalunch.UserTestData.USER_ID;
import static ru.slloc.voteforalunch.VoteTestData.*;

public class VoteServisTest extends AbstractServiceTest {

    @Autowired
    protected VoteService service;

    @Test
    public void delete() throws Exception {
        service.delete(VOTE1_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), VOTE4);
    }

    @Test
    public void deleteNotFound() throws Exception {
       // thrown.expect(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> service.delete(VOTE1_ID, 1));
    }

    @Test
    public void create() throws Exception {
        Vote created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), created, VOTE4, VOTE1);
    }

    @Test
    public void createAfterEndTimeForVote() throws Exception {
        Vote created = getCreatedAfterEndTimeForVote();
        assertThrows(TimeForVoteIsFinishedException.class, () -> service.create(created, USER_ID));
        assertMatch(service.getAll(USER_ID), VOTE4, VOTE1);
    }

    @Test
    public void get() throws Exception {
        Vote actual = service.get(VOTE3_ID, ADMIN_ID);
        assertMatch(actual, VOTE3);
    }

    @Test
    public void getNotFound() throws Exception {
        // thrown.expect(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> service.get(VOTE1_ID, ADMIN_ID));
    }

    @Test
    public void update() throws Exception {
        Vote updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(VOTE1_ID, USER_ID), updated);
    }

    @Test
    public void updateAfterEndTimeForVote() throws Exception {
        Vote updated = getUpdatedAfterEndTimeForVote();
        assertThrows(TimeForVoteIsFinishedException.class, () -> service.update(updated, USER_ID));
        assertMatch(service.getAll(USER_ID), VOTE4, VOTE1);
    }

    @Test
    public void updateNotFound() throws Exception {
        // thrown.expect(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> service.update(VOTE1, ADMIN_ID));
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(USER_ID), VOTE4, VOTE1);
    }

    @Test
    public void getAllForDate() throws Exception {
        assertMatch(service.getAllForDate(DATE_1), VOTE1, VOTE2, VOTE3);
        assertMatch(service.getAllForDate(DATE_2), VOTE4, VOTE5, VOTE6);
    }


}
