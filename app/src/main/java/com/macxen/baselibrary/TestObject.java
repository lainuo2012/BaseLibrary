package com.macxen.baselibrary;

import java.io.Serializable;

/**
 * @author lainuo
 * @name BaseLibrary
 * @class name：com.macxen.baselibrary
 * @time 2018/11/10 13:33
 * @describe describe
 */
public class TestObject implements Serializable {
    private int userId;

    private String userName;

    public TestObject() {
    }

    public TestObject(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
