package com.qakashilliacea.entity;

import com.qakashilliacea.entity.base.BaseEntity;
import com.qakashilliacea.util.constants.Constants;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = Constants.DATABASE_PREFIX + "userInfo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailedInfo extends BaseEntity<Long> {
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "phone_number")
    private String phone;
    @Column(name = "country")
    private String country;
    @Column(name = "birthday")
    private LocalDate birth;
    @Column(name = "gender")
    private String sex;
    @Column(name = "about_me")
    private String about;
    @Column(name = "job")
    private String job;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
