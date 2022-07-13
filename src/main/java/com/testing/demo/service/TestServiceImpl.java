package com.testing.demo.service;

import com.testing.demo.converters.TestConverter;
import com.testing.demo.dto.TestDto;
import com.testing.demo.persistence.entity.TestEntity;
import com.testing.demo.persistence.repository.TestRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

private final TestRepo testRepository;
private final TestConverter testConverter;

    @Override
    public TestEntity createTest(TestDto test) {
    TestEntity pao =  testRepository.save(testConverter.TestDtotoTest(test));
        return pao;
    }
}
