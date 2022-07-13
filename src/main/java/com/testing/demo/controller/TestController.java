package com.testing.demo.controller;


import com.testing.demo.dto.TestDto;
import com.testing.demo.persistence.entity.TestEntity;
import com.testing.demo.service.TestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestServiceImpl testService;

   @PostMapping("/test")
    public ResponseEntity<TestEntity> postTest(@Valid @RequestBody TestDto test){

    return ResponseEntity.ok(testService.createTest(test));

   }
}
