package model;

import helper.UsersHelper;

public class UsersService {
    private final UsersHelper helper;

    public UsersService() {
        helper = new UsersHelper();
    }

    public boolean register(User user) {
        if (helper.userExists(user)) {
            return false;
        }

        helper.register(user);
        return true;
    }
}
