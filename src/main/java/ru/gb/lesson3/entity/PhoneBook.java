/*
 * Copyright (c) 2018.
 */

package ru.gb.lesson3.entity;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private List<User> users;

    public PhoneBook() {
        this.users = new ArrayList<>();
    }

    private static void accept(User item) {
    }

    public void add(User user) {
        users.add(user);
    }

    public String get(String name) {
        String phones = "";
        for (User u: users)
            if (u.getName().equals(name)) phones += ", " + u.getPhone();

        return name + ": " + phones.substring(2, phones.length());
    }
}
