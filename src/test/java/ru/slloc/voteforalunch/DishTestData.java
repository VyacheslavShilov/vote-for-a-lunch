package ru.slloc.voteforalunch;

import ru.slloc.voteforalunch.model.Dish;


import java.time.Month;
import java.util.Arrays;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.slloc.voteforalunch.model.Dish.START_DISH_SEQ;

public class DishTestData {

    public static final int DISH1_ID = START_DISH_SEQ;
    public static final int DISH2_ID = START_DISH_SEQ + 1;
    public static final int DISH3_ID = START_DISH_SEQ + 2;
    public static final int DISH4_ID = START_DISH_SEQ + 3;
    public static final int DISH5_ID = START_DISH_SEQ + 4;

    public static final Dish DISH1 = new Dish(DISH1_ID, "Каша манная", 80, true );
    public static final Dish DISH2 = new Dish(DISH2_ID, "Греческий салат", 150, true );
    public static final Dish DISH3 = new Dish(DISH3_ID, "Каша гречневая", 100, true );
    public static final Dish DISH4 = new Dish(DISH4_ID, "Омары", 400, true );
    public static final Dish DISH5 = new Dish(DISH5_ID, "Артишоки", 320, false );

    public static Dish getCreated(){
        return new Dish(null, "New Food", 999, true);
    }

    public static Dish getUpdated(){
        return new Dish(DISH1.getId(), "Updated Food", 777, true);
    }

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }
}
