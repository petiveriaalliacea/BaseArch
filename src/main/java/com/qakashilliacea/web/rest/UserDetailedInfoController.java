package com.qakashilliacea.web.rest;

import com.qakashilliacea.service.UserDetailedInfoService;
import com.qakashilliacea.util.constants.Constants;
import com.qakashilliacea.web.dto.UserDetailedInfoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

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

    @PostMapping("/changeUserDetails")
    @ApiOperation(value = "change info of logged in user")
    public ResponseEntity changeMyDetails(@ApiParam(value = "Dto with user detailed data") @RequestBody UserDetailedInfoDto dto,
                                          @ApiIgnore @Autowired Principal principal) {
        return ResponseEntity.ok(detailedInfoService.changeMyDetails(dto, principal.getName()));
    }

    @GetMapping("/getByLoggedUser")
    @ApiOperation(value = "get logged user detailed info")
    public ResponseEntity gerLoggedUserInfo(@ApiIgnore @Autowired Principal principal) {
        return ResponseEntity.ok(detailedInfoService.getInfoByUsername(principal.getName()));
    }
}
