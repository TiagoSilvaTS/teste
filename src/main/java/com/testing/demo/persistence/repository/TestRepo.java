package com.testing.demo.persistence.repository;

import com.testing.demo.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestRepo extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

}
