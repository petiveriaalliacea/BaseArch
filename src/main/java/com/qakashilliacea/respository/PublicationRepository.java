package com.qakashilliacea.respository;

import com.qakashilliacea.entity.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long>, JpaSpecificationExecutor<Publication> {
    void deleteById(Long id);

    Page<Publication> findAllByUserId(Long userId, Pageable pageable);

    Optional<Publication> findById(Long id);
}
