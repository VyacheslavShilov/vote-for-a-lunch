package ru.slloc.voteforalunch.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.slloc.voteforalunch.model.Vote;
import ru.slloc.voteforalunch.service.VoteService;
import ru.slloc.voteforalunch.web.AbstractControllerTest;
import ru.slloc.voteforalunch.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.slloc.voteforalunch.TestUtil.readFromJson;
import static ru.slloc.voteforalunch.TestUtil.userHttpBasic;
import static ru.slloc.voteforalunch.VoteTestData.*;
import static ru.slloc.voteforalunch.UserTestData.*;

public class ProfileRestVoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = ProfileRestVoteController.REST_URL + '/';

    @Autowired
    private VoteService voteService;

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + VOTE1_ID).with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(VOTE1));
    }


    @Test
    public void testGetUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL + VOTE1_ID))
                .andExpect(status().isUnauthorized());
    }


    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL).with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(VOTE7, VOTE4, VOTE1));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + VOTE1_ID).with(userHttpBasic(USER)))
                .andExpect(status().isNoContent());
        assertMatch(voteService.getAll(USER_ID), VOTE7, VOTE4);
    }

    @Test
    void testUpdate() throws Exception {
        Vote updated = getUpdated();

        mockMvc.perform(put(REST_URL + VOTE1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)).with(userHttpBasic(USER)))
                .andExpect(status().isOk());

        assertMatch(voteService.get(VOTE1_ID, USER_ID), updated);
    }

    @Test
    void testCreate() throws Exception {
        Vote created = getCreated();

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)).with(userHttpBasic(USER)));

        Vote returned = readFromJson(action, Vote.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(voteService.getAll(USER_ID), created, VOTE7, VOTE4, VOTE1);
    }



}
