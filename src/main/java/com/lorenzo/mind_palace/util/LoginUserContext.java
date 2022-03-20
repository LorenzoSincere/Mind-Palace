package com.lorenzo.mind_palace.util;

import com.lorenzo.mind_palace.response.UserLoginResp;

import java.io.Serializable;

/**
 * @author libocheng
 */
public class LoginUserContext implements Serializable {

    private static ThreadLocal<UserLoginResp> user = new ThreadLocal<>();

    public static UserLoginResp getUser() {
        return user.get();
    }

    public static void setUser(UserLoginResp user) {
        LoginUserContext.user.set(user);
    }

}
