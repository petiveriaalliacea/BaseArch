package com.qakashilliacea.respository;

import com.qakashilliacea.entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {
    EmailVerification findEmailVerificationByVerificationKey(String verificationKey);

    Optional<EmailVerification> findEmailVerificationByUserId(Long userId);
}
