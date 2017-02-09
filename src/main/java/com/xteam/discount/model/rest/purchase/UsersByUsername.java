package com.xteam.discount.model.rest.purchase;

import java.util.ArrayList;
import java.util.List;

public class UsersByUsername {

    private List<User> users;

    public List<User> getUsers() {
        if(users == null) {
            users = new ArrayList<>();
        }
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UsersByUsername[" + getUsers().toString() + "]";
    }
}
