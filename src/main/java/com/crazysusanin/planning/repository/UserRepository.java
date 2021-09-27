package com.crazysusanin.planning.repository;

import com.crazysusanin.planning.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);
    User findByUsername(String username);
    User findByEmail(String email);
}
