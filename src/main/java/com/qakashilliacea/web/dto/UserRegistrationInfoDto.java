package com.qakashilliacea.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UserRegistrationInfoDto", description = "dto with register data")
public class UserRegistrationInfoDto {
    private String password;
    private String firstname;
    private String lastname;
    private String patronymic;
    private String phone;
    private String country;
    private String username;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birth;
    private String sex;
    private String about;
    private String job;
}
