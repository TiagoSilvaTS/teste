package com.testing.demo.converters;

import com.testing.demo.dto.TestDto;
import com.testing.demo.persistence.entity.TestEntity;
import org.springframework.stereotype.Component;


@Component
public class TestConverter {


public TestEntity TestDtotoTest(TestDto test){
    return TestEntity.builder()
            .name(test.getName()).build();
}

}
