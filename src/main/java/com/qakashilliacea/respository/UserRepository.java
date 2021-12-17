package com.qakashilliacea.respository;

import com.qakashilliacea.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUsername(String username);

    boolean existsById(Long id);

    User findByUsername(String username);

    User findUserById(Long id);
}
