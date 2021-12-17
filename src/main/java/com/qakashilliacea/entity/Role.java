package com.qakashilliacea.entity;

import com.qakashilliacea.entity.base.BaseEntity;
import com.qakashilliacea.util.constants.Constants;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Constants.DATABASE_PREFIX + "role")
public class Role extends BaseEntity<Long> {
    @Column(name = "name")
    private String name;

    public static final Long ROLE_USER = 1L;
    public static final Long ROLE_ADMIN = 2L;
}
