package model;

import helper.UsersHelper;

public class Authentication {
    private final UsersHelper helper;

    public Authentication() {
        helper = new UsersHelper();
    }

    public User authenticate(String username, String password) {
        return helper.authenticate(username, password);
    }
}
