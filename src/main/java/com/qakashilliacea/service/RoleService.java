package com.qakashilliacea.service;

import com.qakashilliacea.entity.Role;
import com.qakashilliacea.web.dto.ResponseDto;

import java.util.List;

public interface RoleService {
    ResponseDto getById (Long id);
    ResponseDto getAll();
}
