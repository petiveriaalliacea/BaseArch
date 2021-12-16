package com.qakashilliacea.respository;

import com.qakashilliacea.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;


@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    void deleteById(Long id);

    Optional<Publication> findPublicationById(Long id);

    Optional<Publication> findPublicationByUserId(Long userId);

    Optional<Publication> findPublicationByCreatedAt(LocalDate createdAt);
}
