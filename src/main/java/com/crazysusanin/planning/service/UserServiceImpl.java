package com.crazysusanin.planning.service;

import com.crazysusanin.planning.model.Role;
import com.crazysusanin.planning.model.User;
import com.crazysusanin.planning.repository.UserRepository;
import com.crazysusanin.planning.repository.RoleRepository;
import com.crazysusanin.planning.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPasswordConfirm(bCryptPasswordEncoder.encode(user.getPasswordConfirm()));
        if(user.getRole() == null){
            Role role = roleRepository.findByType(AppConstants.ROLE_DEFAULT);
            user.setRole(role);
        }
        if (userRepository.save(user) != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean activateUser(String name) {
       User user =  userRepository.findByUsername(name);
       user.setActive(true);
       userRepository.save(user);
        return true;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAllByRole(Role role) {
        return userRepository.findAllByRole(role);
    }
}
