package com.qakashilliacea.respository;

import com.qakashilliacea.entity.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    void deleteById(Long id);

    Page<Publication> findAllByUserId(Long userId, Pageable pageable);

    Optional<Publication> findByCreatedAt(LocalDate createdAt);
}
