package com.qakashilliacea.respository;

import com.qakashilliacea.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUsername(String username);

    boolean existsById(Long id);

    Optional<User> findByUsername(String username);
}
