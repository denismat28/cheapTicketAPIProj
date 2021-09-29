package com.crazysusanin.planning.repository;

import com.crazysusanin.planning.model.Role;
import com.crazysusanin.planning.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAllByRole (Role role);
}
