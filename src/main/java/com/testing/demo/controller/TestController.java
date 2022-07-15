package com.testing.demo.controller;


import com.testing.demo.dto.CredentialsDto;
import com.testing.demo.dto.LoginDto;
import com.testing.demo.dto.UserDto;
import com.testing.demo.persistence.entity.UserEntity;
import com.testing.demo.service.TestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<LoginDto> login(@Valid @RequestBody CredentialsDto log){
       var result = testService.login(log);
        ResponseCookie cookie = ResponseCookie
                .from("cookie_auth",result.getToken())
                .httpOnly(true)
                .sameSite("None")
                .secure(true)
                .maxAge(24 * 60 * 60)
                .path("/")
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE,cookie.toString())
                .body(result);
    }

    @GetMapping("/admin")
    @PreAuthorize("@authorized.hasRole('ADMIN')")
    public ResponseEntity<String> adminTest(){

        return ResponseEntity.ok("HELLO WORLD");
    }
}
