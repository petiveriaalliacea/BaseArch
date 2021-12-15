package com.qakashilliacea.service.impl;

import com.qakashilliacea.entity.Role;
import com.qakashilliacea.respository.RoleRepository;
import com.qakashilliacea.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getById(Long id) {

        return null;
    }
}
