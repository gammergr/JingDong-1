package com.bwie.testten.mine.bean;

/**
 * Created by 姜天赐 on 2017/12/1.
 */
public class LoginEvent {
    private String username;

    public LoginEvent(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
