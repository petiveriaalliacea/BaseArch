package com.qakashilliacea.service.impl;

import com.qakashilliacea.respository.RoleRepository;
import com.qakashilliacea.service.RoleService;
import com.qakashilliacea.util.ErrorMessages;
import com.qakashilliacea.util.ObjectsMapper;
import com.qakashilliacea.web.dto.ResponseDto;
import com.qakashilliacea.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public ResponseDto getAll() {
        return ResponseDto.builder()
                .success(true)
                .data(
                        roleRepository.findAll()
                                .stream()
                                .map(ObjectsMapper::converToRoleDto)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public ResponseDto getById(Long id) {
        ResponseDto response = new ResponseDto<UserDto>();
        if (!roleRepository.existsById(id)) {
            response.setStatus(404);
            response.setErrorMessage(ErrorMessages.cantFindEntityById("Role", id));
            return response;
        }
        response.setSuccess(true);
        response.setData(ObjectsMapper.converToRoleDto(roleRepository.getById(id)));
        return response;
    }
}
