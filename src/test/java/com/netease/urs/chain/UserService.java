package com.netease.urs.chain;

/**
 * Created by hzliuxin1 on 2017/8/9.
 */
public class UserService {

    public String loadUser(String username, String password) {
        String result = "username: " + username + " password: " + password;
        System.out.println(result);
        return result;
    }
}
