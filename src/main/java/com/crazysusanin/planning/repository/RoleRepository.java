package com.crazysusanin.planning.repository;

import com.crazysusanin.planning.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Role findByType(String type);
}
