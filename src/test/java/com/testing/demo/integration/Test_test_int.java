package com.testing.demo.integration;

import com.testing.demo.dto.TestDto;
import com.testing.demo.persistence.entity.TestEntity;
import com.testing.demo.service.TestServiceImpl;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class Test_test_int {

     @Autowired
    private  TestServiceImpl testService;

    @Test
    void methodCreateTestShouldReturnTestEntity(){
      var testEntity = new TestDto("pao");
      var result = testService.createTest(testEntity);

        Assertions.assertEquals(testEntity.getName(),result.getName());

    }
}
