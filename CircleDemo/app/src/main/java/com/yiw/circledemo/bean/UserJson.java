package com.yiw.circledemo.bean;

/**
 * Created by hasee-pc on 2018/03/07.
 */

public class UserJson {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserJson(User user) {
        this.username=user.getName();
    }
}
