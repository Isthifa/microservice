package com.example.iam.repository;

import com.example.iam.entity.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserLogRepository extends JpaRepository<UserLog, UUID>{
    Optional<UserLog> findByUsername(String username);

}
