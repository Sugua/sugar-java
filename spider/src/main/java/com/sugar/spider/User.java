package com.sugar.spider;

/**
 * @Description TODO
 * @Author sugar
 * @Date 2020/8/7 11:53 AM
 * @Version 1.0
 */
public class User {
    private String username;
    private String password;

    public User(){

    }

    public User(String username, String password) {

        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }
}
