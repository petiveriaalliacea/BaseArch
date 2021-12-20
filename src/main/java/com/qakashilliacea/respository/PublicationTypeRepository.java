package com.qakashilliacea.respository;

import com.qakashilliacea.entity.PublicationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublicationTypeRepository extends JpaRepository<PublicationType, Long> {
    Optional<PublicationType> findByType(String type);
}
