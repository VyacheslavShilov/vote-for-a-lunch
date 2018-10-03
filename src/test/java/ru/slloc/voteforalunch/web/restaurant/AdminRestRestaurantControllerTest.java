package ru.slloc.voteforalunch.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.slloc.voteforalunch.TestUtil;
import ru.slloc.voteforalunch.model.Restaurant;
import ru.slloc.voteforalunch.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.slloc.voteforalunch.RestaurantTestData.*;
import static ru.slloc.voteforalunch.TestUtil.*;
import static ru.slloc.voteforalunch.UserTestData.ADMIN;
import static ru.slloc.voteforalunch.UserTestData.USER;

public class AdminRestRestaurantControllerTest extends AbstractControllerRestaurantTest {
    private static final String REST_URL = AdminRestRestaurantController.REST_URL + '/';

    @Test
    void testGetAll() throws Exception {
        TestUtil.print(
                mockMvc.perform(get(REST_URL).with(userAuth(ADMIN)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andExpect(contentJsonArray(RESTAURANT1, RESTAURANT2))
        );
    }

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT2_ID).with(userAuth(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT2));
    }

    @Test
    void testGetWinnerWithData() throws Exception{
        mockMvc.perform(get(REST_URL + "get_winner")
                .param("date", "2018-04-09").with(userAuth(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(contentJsonArray(RESTAURANT1));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT1_ID).with(userAuth(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(restaurantService.getAll(), RESTAURANT2);
    }

    @Test
    void testGetByName() throws Exception {
        mockMvc.perform(get(REST_URL + "by?name=" + RESTAURANT1.getName()).with(userAuth(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT1));
    }

    @Test
    void testUpdate() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT1);
        updated.setName("UpdatedName");
        mockMvc.perform(put(REST_URL + RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)).with(userAuth(ADMIN)))
                .andExpect(status().isOk());

        assertMatch(restaurantService.get(RESTAURANT1_ID), updated);
    }

    @Test
    void testCreate() throws Exception {
        Restaurant expected = new Restaurant(null, "NewRest");
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)).with(userAuth(ADMIN)))
                .andExpect(status().isCreated());

        Restaurant returned = TestUtil.readFromJson(action, Restaurant.class);
        expected.setId(returned.getId());

        assertMatch(returned, expected);
        assertMatch(restaurantService.getAll(), expected, RESTAURANT2, RESTAURANT1);
    }

    @Test
    public void testGetUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }
}
