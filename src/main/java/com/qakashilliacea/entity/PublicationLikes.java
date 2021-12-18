package com.qakashilliacea.entity;

import com.qakashilliacea.entity.base.AuditingEntity;
import com.qakashilliacea.util.constants.Constants;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = Constants.DATABASE_PREFIX + "publication_likes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicationLikes extends AuditingEntity {
    @Column(name = "publication_id")
    private Long publicationId;
}
