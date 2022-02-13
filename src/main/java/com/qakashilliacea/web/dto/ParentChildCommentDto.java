package com.qakashilliacea.web.dto;

import com.qakashilliacea.entity.Comment;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParentChildCommentDto extends CommentInfoDto {
    private List<CommentInfoDto> childComments = new ArrayList<>();

    public ParentChildCommentDto (Comment comment, List<CommentInfoDto> childComments) {
        this.setId(comment.getId());
        this.setCreatedDate(comment.getCreatedDate());
        this.setUserId(comment.getCreatedBy().getId());
        this.setDescription(comment.getDescription());
        this.setPublicationId(comment.getPublicationId());

        this.setChildComments(childComments);
    }
}
