package ru.slloc.voteforalunch.web;


import ru.slloc.voteforalunch.model.User;

public class SecurityUtil {

    private static int id = User.START_USER_SEQ;

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }

}