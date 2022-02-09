package com.qakashilliacea.entity;

import com.qakashilliacea.entity.base.BaseEntity;
import com.qakashilliacea.util.constants.Constants;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = Constants.DATABASE_PREFIX + "user")
public class User extends BaseEntity<Long> {
    @Column(name = "username", columnDefinition = "VARCHAR")
    private String username;

    @Column(name = "password", columnDefinition = "VARCHAR")
    private String password;

    @Column(name = "verified_email")
    @Builder.Default
    private Boolean verified = false;

    @ManyToMany
    private Set<Role> roles = new HashSet<>();
}
