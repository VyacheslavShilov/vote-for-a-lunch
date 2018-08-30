package ru.slloc.voteforalunch;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.slloc.voteforalunch.model.Vote;
import ru.slloc.voteforalunch.service.RestaurantService;
import ru.slloc.voteforalunch.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static ru.slloc.voteforalunch.RestaurantTestData.RESTAURANT1;
import static ru.slloc.voteforalunch.RestaurantTestData.RESTAURANT2;
import static ru.slloc.voteforalunch.UserTestData.ADMIN;
import static ru.slloc.voteforalunch.UserTestData.USER;
import static ru.slloc.voteforalunch.UserTestData.USER2;
import static ru.slloc.voteforalunch.model.Vote.START_VOTE_SEQ;


public class VoteTestData {

    public static final int VOTE1_ID = START_VOTE_SEQ;
    public static final int VOTE3_ID = START_VOTE_SEQ + 2;

    public static final Vote VOTE1 = new Vote(START_VOTE_SEQ, of(2018, Month.APRIL, 9, 9,0), USER, RESTAURANT1);
    public static final Vote VOTE2 = new Vote(START_VOTE_SEQ + 1, of(2018, Month.APRIL, 9, 9,30), USER2, RESTAURANT1);
    public static final Vote VOTE3 = new Vote(START_VOTE_SEQ + 2, of(2018, Month.APRIL, 9, 10,0), ADMIN, RESTAURANT2);
    public static final Vote VOTE4 = new Vote(START_VOTE_SEQ + 3, of(2018, Month.APRIL, 10, 9,0), USER, RESTAURANT2);
    public static final Vote VOTE5 = new Vote(START_VOTE_SEQ + 4, of(2018, Month.APRIL, 10, 9,30), USER2, RESTAURANT1);
    public static final Vote VOTE6 = new Vote(START_VOTE_SEQ + 5, of(2018, Month.APRIL, 10, 10,0), ADMIN, RESTAURANT2);

    public static final List<Vote> VOTES = Arrays.asList(VOTE6, VOTE5, VOTE4, VOTE3, VOTE2, VOTE1);

    public static Vote getCreated() {
        return new Vote(null, of(2018, Month.MAY, 1, 8,0), USER, RESTAURANT1);
    }

    public static Vote getUpdated() {
        return new Vote(VOTE1.getId(), VOTE1.getDateTime(), USER, RESTAURANT2);
    }

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user");
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user").isEqualTo(expected);
    }
}
