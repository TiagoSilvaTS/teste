package com.testing.demo.converters;

import com.testing.demo.dto.UserDto;
import com.testing.demo.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;


@Component
public class TestConverter {


public UserEntity TestDtotoTest(UserDto test){
    return UserEntity.builder()
            .name(test.getName())
            .email(test.getEmail())
            .role(test.getRole())
            .password(test.getPassword())
            .build();
}

}
