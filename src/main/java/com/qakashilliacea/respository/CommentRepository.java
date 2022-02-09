package com.qakashilliacea.respository;

import com.qakashilliacea.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByPublication_Id (Long publicationId, Pageable pageable);
    Page<Comment> findAllByPublication_IdAndParentIdIsNull (Long publicationId, Pageable pageable);
    Page<Comment> findAllByParentIdEquals(Long parentId, Pageable pageable);
    List<Comment> findAllByParentIdIn(List<Long> parentId);
}
