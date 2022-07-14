package com.testing.demo.integration;

import com.testing.demo.dto.UserDto;
import com.testing.demo.enums.UserType;
import com.testing.demo.service.TestServiceImpl;
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
      var testEntity = new UserDto("pao","teste","teste", UserType.ADMIN);
      var result = testService.createTest(testEntity);

        Assertions.assertEquals(testEntity.getName(),result.getName());

    }
}
