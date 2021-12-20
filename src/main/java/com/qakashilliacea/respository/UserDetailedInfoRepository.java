package com.qakashilliacea.respository;

import com.qakashilliacea.entity.User;
import com.qakashilliacea.entity.UserDetailedInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailedInfoRepository extends JpaRepository<UserDetailedInfo, Long> {
    Optional<UserDetailedInfo> findByUser(User user);
}
