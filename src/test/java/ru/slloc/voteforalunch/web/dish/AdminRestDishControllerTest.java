package ru.slloc.voteforalunch.web.dish;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.slloc.voteforalunch.model.Dish;
import ru.slloc.voteforalunch.service.DishService;
import ru.slloc.voteforalunch.web.AbstractControllerTest;
import ru.slloc.voteforalunch.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.slloc.voteforalunch.DishTestData.*;
import static ru.slloc.voteforalunch.RestaurantTestData.RESTAURANT1_ID;
import static ru.slloc.voteforalunch.RestaurantTestData.RESTAURANT2_ID;
import static ru.slloc.voteforalunch.TestUtil.*;

public class AdminRestDishControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestDishController.REST_URL + '/';

@Autowired
private DishService service;

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID  + "/dishes/" + DISH1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(DISH1));
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT2_ID  + "/dishes/"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(DISH5, DISH4, DISH2));
    }


    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT1_ID  + "/dishes/" + DISH3_ID))
                .andExpect(status().isNoContent());
        assertMatch(service.getAll(RESTAURANT1_ID), DISH1);
    }

    @Test
    void testUpdate() throws Exception {
        Dish updated = getUpdated();

        mockMvc.perform(put(REST_URL + RESTAURANT1_ID  + "/dishes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        assertMatch(service.get(DISH1_ID, RESTAURANT1_ID), updated);
    }

    @Test
    void testCreate() throws Exception {
        Dish created = getCreated();

        ResultActions action = mockMvc.perform(post(REST_URL + RESTAURANT1_ID  + "/dishes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));

        Dish returned = readFromJson(action, Dish.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(service.getAll(RESTAURANT1_ID), created, DISH3, DISH1);
    }

}
