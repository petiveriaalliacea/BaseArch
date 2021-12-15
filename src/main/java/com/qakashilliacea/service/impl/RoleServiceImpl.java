package com.qakashilliacea.service.impl;

import com.qakashilliacea.entity.Role;
import com.qakashilliacea.respository.RoleRepository;
import com.qakashilliacea.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return null;
    }

    @Override
    public Role getById(Long id) {

        return null;
    }
}
