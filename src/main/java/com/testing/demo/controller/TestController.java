package com.testing.demo.controller;


import com.testing.demo.dto.LoginDto;
import com.testing.demo.dto.UserDto;
import com.testing.demo.persistence.entity.UserEntity;
import com.testing.demo.service.TestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class TestController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final TestServiceImpl testService;

   @PostMapping("/test")
    public ResponseEntity<UserEntity> postTest(@Valid @RequestBody UserDto test){
    test.setPassword(passwordEncoder.encode(test.getPassword()));
    return ResponseEntity.ok(testService.createTest(test));
   }
    @PostMapping("/login")
    public ResponseEntity<UserEntity> login(@Valid @RequestBody LoginDto log){

        return ResponseEntity.ok(testService.login(log));
    }
}
