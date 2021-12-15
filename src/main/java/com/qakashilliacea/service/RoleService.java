package com.qakashilliacea.service;

import com.qakashilliacea.entity.Role;

import java.util.List;

public interface RoleService {
    Role getById (Long id);
    List<Role> getAll();
}
