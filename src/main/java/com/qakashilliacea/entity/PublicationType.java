package com.qakashilliacea.entity;

import com.qakashilliacea.entity.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static com.qakashilliacea.util.constants.Constants.DATABASE_PREFIX;

@Entity
@Table(name = DATABASE_PREFIX + "publication_type")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicationType extends BaseEntity<Long> {
    @Column(name = "type")
    private String type;
}
