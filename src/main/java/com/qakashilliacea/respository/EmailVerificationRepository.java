package com.qakashilliacea.respository;

import com.qakashilliacea.entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {
    Optional<EmailVerification> findEmailVerificationByUserId(Long userId);

    Optional<EmailVerification> findByCodeAndVerificationKey(String code, String verificationKey);
}
