package com.example.naickerbhavesh.retrofitcrud.models;

import java.util.List;

public class UsersResponse {
    boolean error;
    List<User> users;

    public UsersResponse(boolean error, List<User> users) {
        this.error = error;
        this.users = users;
    }

    public boolean isError() {
        return error;
    }

    public List<User> getUsers() {
        return users;
    }
}
