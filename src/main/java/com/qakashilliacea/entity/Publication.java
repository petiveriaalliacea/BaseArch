package com.qakashilliacea.entity;

import com.qakashilliacea.entity.base.BaseEntity;
import com.qakashilliacea.util.constants.Constants;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = Constants.DATABASE_PREFIX + "publications")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Publication extends BaseEntity<Long> {
    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "publication_created_date")
    private LocalDate createdAt;

    @Column(name = "views")
    private Integer views;

    @Column(name = "likes")
    @Builder.Default
    private Integer amountOfLikes = 0;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "type_id")
    private Long typeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;
}
