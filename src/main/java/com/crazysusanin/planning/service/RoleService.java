package com.crazysusanin.planning.service;

import com.crazysusanin.planning.model.Role;

public interface RoleService {
    Role findByType(String type);
}
