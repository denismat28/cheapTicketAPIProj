package com.crazysusanin.planning.service;

import com.crazysusanin.planning.model.Role;
import com.crazysusanin.planning.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findByType(String name) {
        return roleRepository.findByType(name);
    }

    @Override
    public Role findById(int id) {
        return roleRepository.getOne(id);
    }
}
