package com.testing.demo.service;

import com.testing.demo.dto.CredentialsDto;
import com.testing.demo.dto.LoginDto;
import com.testing.demo.dto.UserDto;
import com.testing.demo.persistence.entity.UserEntity;

public interface TestService {

   public UserEntity createTest(UserDto test);
   public LoginDto login(CredentialsDto log);

}
