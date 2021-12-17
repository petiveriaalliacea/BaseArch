package com.qakashilliacea.respository;

import com.qakashilliacea.entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {
    EmailVerification findEmailVerificationByVerificationKey(String verificationKey);

    EmailVerification findEmailVerificationByUserId(Long userId);
}
