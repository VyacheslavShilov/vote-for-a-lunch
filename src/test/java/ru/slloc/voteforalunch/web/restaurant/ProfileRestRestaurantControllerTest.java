package ru.slloc.voteforalunch.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import ru.slloc.voteforalunch.TestUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.slloc.voteforalunch.RestaurantTestData.*;
import static ru.slloc.voteforalunch.TestUtil.contentJson;
import static ru.slloc.voteforalunch.TestUtil.contentJsonArray;
public class ProfileRestRestaurantControllerTest extends AbstractControllerRestaurantTest{

    private static final String REST_URL = ProfileRestRestaurantController.REST_URL + '/';

    @Test
    void testGetAll() throws Exception {
        TestUtil.print(
                mockMvc.perform(get(REST_URL))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andExpect(contentJsonArray(RESTAURANT1, RESTAURANT2))
        );
    }

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT2_ID))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT2));
    }

    @Test
    void testGetWinnerWithData() throws Exception{
        mockMvc.perform(get(REST_URL + "get_winner")
        .param("date", "2018-04-09"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(contentJsonArray(RESTAURANT1));
    }

}
