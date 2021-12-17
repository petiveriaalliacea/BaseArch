package com.qakashilliacea.entity;

import com.qakashilliacea.entity.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "email_verification")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailVerification extends BaseEntity<Long> {
    @Column(name = "verificationKey")
    private String verificationKey;

    @Column(name = "user_id")
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;
}
