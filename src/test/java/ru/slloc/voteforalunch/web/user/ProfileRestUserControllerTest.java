package ru.slloc.voteforalunch.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import ru.slloc.voteforalunch.TestUtil;
import ru.slloc.voteforalunch.model.Role;
import ru.slloc.voteforalunch.model.User;
import ru.slloc.voteforalunch.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.slloc.voteforalunch.TestUtil.userHttpBasic;
import static ru.slloc.voteforalunch.UserTestData.*;
import static ru.slloc.voteforalunch.web.user.ProfileRestUserController.REST_URL;

class ProfileRestUserControllerTest extends AbstractControllerUserTest {

    @Test
    void testGet() throws Exception {
        TestUtil.print(
                mockMvc.perform(get(REST_URL).with(userHttpBasic(USER)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andExpect(contentJson(USER))
        );
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL).with(userHttpBasic(USER)))
                .andExpect(status().isNoContent());
        assertMatch(userService.getAll(), ADMIN, USER2);
    }

    @Test
    void testUpdate() throws Exception {
        User updated = new User(USER_ID, "newName", "newemail@ya.ru", "newPassword", Role.ROLE_USER);
        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)).with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isOk());

        assertMatch(new User(userService.getByEmail("newemail@ya.ru")), updated);
    }

    @Test
    public void testGetUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }
}