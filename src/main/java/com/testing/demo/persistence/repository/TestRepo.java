package com.testing.demo.persistence.repository;

import com.testing.demo.persistence.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepo extends JpaRepository<TestEntity, Long> {


}
