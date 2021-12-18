package com.qakashilliacea.config;

import com.qakashilliacea.entity.User;
import com.qakashilliacea.service.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;
import java.time.LocalDateTime;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider", dateTimeProviderRef = "auditingDateTimeProvider")
@EnableTransactionManagement
@RequiredArgsConstructor
public class AuditingConfig {
    private final CurrentUserService currentUserService;

    @Bean
    public AuditorAware<User> auditorProvider() {
        return () -> Optional.of(currentUserService.getCurrentUser());
    }

    @Bean
    public DateTimeProvider auditingDateTimeProvider() {
        return () -> Optional.of(LocalDateTime.now());
    }
}
