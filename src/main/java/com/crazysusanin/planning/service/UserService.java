package com.crazysusanin.planning.service;

import com.crazysusanin.planning.model.Role;
import com.crazysusanin.planning.model.User;

import java.util.List;

public interface UserService {
    boolean save(User user);
    boolean activateUser(String name);
    User findByUsername(String username);
    User findById(int id);
    List<User> findAllByRole (Role role);
}
