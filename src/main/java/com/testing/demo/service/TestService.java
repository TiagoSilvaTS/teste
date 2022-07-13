package com.testing.demo.service;

import com.testing.demo.dto.TestDto;
import com.testing.demo.persistence.entity.TestEntity;

public interface TestService {

   public TestEntity createTest(TestDto test);

}
