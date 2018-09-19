package ru.slloc.voteforalunch;

import ru.slloc.voteforalunch.model.Vote;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.slloc.voteforalunch.RestaurantTestData.RESTAURANT1;
import static ru.slloc.voteforalunch.RestaurantTestData.RESTAURANT2;
import static ru.slloc.voteforalunch.UserTestData.*;
import static ru.slloc.voteforalunch.model.Vote.END_TIME_FOR_VOTE;
import static ru.slloc.voteforalunch.model.Vote.START_VOTE_SEQ;


public class VoteTestData {

    public static final int VOTE1_ID = START_VOTE_SEQ;
    public static final int VOTE3_ID = START_VOTE_SEQ + 2;

    public static final LocalDate DATE_1 = LocalDate.of(2018, Month.APRIL, 9);
    public static final LocalDate DATE_2 = LocalDate.of(2018, Month.APRIL, 10);
    public static final LocalDate DATE_3 = LocalDate.of(2018, Month.APRIL, 11);

    public static final Vote VOTE1 = new Vote(START_VOTE_SEQ, of(DATE_1, LocalTime.of(9, 0)), USER, RESTAURANT1);
    public static final Vote VOTE2 = new Vote(START_VOTE_SEQ + 1, of(DATE_1, LocalTime.of(9, 30)), USER2, RESTAURANT1);
    public static final Vote VOTE3 = new Vote(START_VOTE_SEQ + 2, of(DATE_1, LocalTime.of(10, 0)), ADMIN, RESTAURANT2);
    public static final Vote VOTE4 = new Vote(START_VOTE_SEQ + 3, of(DATE_2, LocalTime.of(9, 0)), USER, RESTAURANT2);
    public static final Vote VOTE5 = new Vote(START_VOTE_SEQ + 4, of(DATE_2, LocalTime.of(9, 30)), USER2, RESTAURANT1);
    public static final Vote VOTE6 = new Vote(START_VOTE_SEQ + 5, of(DATE_2, LocalTime.of(10, 0)), ADMIN, RESTAURANT2);
    public static final Vote VOTE7 = new Vote(START_VOTE_SEQ + 6, of(DATE_3, LocalTime.of(0, 1)), USER, RESTAURANT2);
    public static final Vote VOTE8 = new Vote(START_VOTE_SEQ + 7, of(DATE_3, LocalTime.of(10, 59)), USER2, RESTAURANT1);
    public static final Vote VOTE9 = new Vote(START_VOTE_SEQ + 8, of(DATE_3, LocalTime.of(11, 1)), ADMIN, RESTAURANT2);
    public static final Vote ADMIN_VOTE1 = VOTE3;
    public static final Vote ADMIN_VOTE2 = VOTE6;
    public static final Vote ADMIN_VOTE3 = VOTE9;



    public static final List<Vote> VOTES = Arrays.asList(VOTE6, VOTE5, VOTE4, VOTE3, VOTE2, VOTE1);

    public static Vote getCreated() {
        return new Vote(null, of(2018, Month.MAY, 1, 8,0), USER, RESTAURANT1);
    }

    public static Vote getCreatedAfterEndTimeForVote() {
        return new Vote(null, of(2018, Month.MAY, 1, END_TIME_FOR_VOTE.getHour() + 1,END_TIME_FOR_VOTE.getMinute() + 1), USER, RESTAURANT1);
    }

    public static Vote getUpdated() {
        return new Vote(VOTE1.getId(), VOTE1.getDateTime(), USER, RESTAURANT2);
    }

    public static Vote getUpdatedAfterEndTimeForVote() {
        return new Vote(null, of(2018, Month.MAY, 1, END_TIME_FOR_VOTE.getHour() + 1,END_TIME_FOR_VOTE.getMinute() + 1), USER, RESTAURANT1);
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
