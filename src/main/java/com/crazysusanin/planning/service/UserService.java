package com.crazysusanin.planning.service;

import com.crazysusanin.planning.model.User;

public interface UserService {
    boolean save(User user);
    boolean activateUser(String name);
    User findByUsername(String username);
    User findById(int id);
}
