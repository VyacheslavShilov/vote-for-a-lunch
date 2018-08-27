package ru.slloc.voteforalunch;

import ru.slloc.voteforalunch.model.Restaurant;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.slloc.voteforalunch.model.Restaurant.START_RESTAURANT_SEQ;

public class RestaurantTestData {
    public static final int RESTAURANT1_ID = START_RESTAURANT_SEQ;
    public static final int RESTAURANT2_ID = START_RESTAURANT_SEQ + 1;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Стрела");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT2_ID, "Заря");

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields().isEqualTo(expected);
    }
}
