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

    public void add(User user) {
        users.add(user);
    }

    public StringBuilder get(String name) {
        StringBuilder phones = new StringBuilder();
        phones.append(name);
        phones.append(": ");
        String phone;
        Boolean bFirst = true;
        for (User u: users)
            if (u.getName().equals(name)) {
                phone = u.getPhone().trim();
                if (!phone.isEmpty()) {
                    if (bFirst) bFirst = false;
                    else phones.append(", ");
                    phones.append(phone);
                }
            }
        return phones;
    }
}
