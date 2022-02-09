package com.qakashilliacea.entity;

import com.qakashilliacea.entity.base.AuditingEntity;
import com.qakashilliacea.util.constants.Constants;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = Constants.DATABASE_PREFIX + "comment")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends AuditingEntity {
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "publication_id")
    private Long publicationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Publication publication;
}
