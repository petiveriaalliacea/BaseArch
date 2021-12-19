package com.qakashilliacea.web.rest;

import com.qakashilliacea.service.UserDetailedInfoService;
import com.qakashilliacea.util.constants.Constants;
import com.qakashilliacea.web.dto.UserDetailedInfoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constants.PRIVATE_API_ENDPOINT + "/userData")
@ApiModel(value = "UserDetailedInfoController", description = "user detailed data controller")
public class UserDetailedInfoController {
    private final UserDetailedInfoService detailedInfoService;

    @GetMapping("/getByUserId/{userId}")
    @ApiOperation(value = "get user detailed info by user id")
    public ResponseEntity getByUserId(@ApiParam(value = "user id") @PathVariable("userId") Long userId) {
        return ResponseEntity.ok(detailedInfoService.getByUserId(userId));
    }

    @GetMapping("/getById/{id}")
    @ApiOperation(value = "get user detailed info by id")
    public ResponseEntity getById(@ApiParam(value = "id") @PathVariable("id") Long id) {
        return ResponseEntity.ok(detailedInfoService.getById(id));
    }

    @PostMapping("/changeUserDetails")
    @ApiOperation(value = "changeUserInfo")
    public ResponseEntity changeUserDetails(@ApiParam(value = "Dto with user detailed data")
                                            @RequestBody UserDetailedInfoDto dto) {
        return ResponseEntity.ok(detailedInfoService.changeDetails(dto));
    }

    @GetMapping("/getByLoggedUser")
    @ApiOperation(value = "get logged user detailed info")
    public ResponseEntity gerLoggedUserInfo() {
        return ResponseEntity.ok(detailedInfoService.getByLoggedUserInfo());
    }
}
