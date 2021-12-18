package com.qakashilliacea.respository;

import com.qakashilliacea.entity.PublicationLikes;
import com.qakashilliacea.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublicationLikesRepository extends JpaRepository<PublicationLikes, Long> {
    Optional<PublicationLikes> findByPublicationIdAndCreatedBy(Long publicationId, User user);

    void deleteByPublicationId(Long publicationId);
}
