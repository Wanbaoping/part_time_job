package com.baoge.utils;

import com.baoge.entity.User;

import javax.servlet.http.HttpSession;

public class UserUtils {
    public static User getSubjectUser(HttpSession session) {
        return (User) session.getAttribute("loginUser");
    }
    public static User getSubjectFontUser(HttpSession session) {
        return (User) session.getAttribute("frontuser");
    }
}
