package com.testing.demo.service;

import com.testing.demo.converters.TestConverter;
import com.testing.demo.dto.CredentialsDto;
import com.testing.demo.dto.LoginDto;
import com.testing.demo.dto.PrincipalDto;
import com.testing.demo.dto.UserDto;
import com.testing.demo.exception.authExeptions.WrongCredentialsException;
import com.testing.demo.persistence.entity.UserEntity;
import com.testing.demo.persistence.repository.TestRepo;
import com.testing.demo.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    @Autowired
    private PasswordEncoder passwordEncoder;

private final TestRepo testRepository;

private final TestConverter testConverter;

    private final JwtService jwt;

    @Override
    public UserEntity createTest(UserDto test)  {
    UserEntity pao =  testRepository.save(testConverter.TestDtotoTest(test));
        return pao;
    }

    @Override
    public LoginDto login(CredentialsDto log) {
    var user = testRepository.findByEmail(log.getEmail()).orElseThrow( () ->{
       throw new WrongCredentialsException("WRONG_LOGIN_INFORMATION");
   });
        if (!passwordEncoder.matches(log.getPassword(), user.getPassword())) {
            throw new WrongCredentialsException("WRONG_LOGIN_INFORMATION");
        }
        PrincipalDto principalDto = PrincipalDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .build();
        String token = jwt.createToken(principalDto);
        LoginDto loginDto = LoginDto.builder().principalDto(principalDto).token(token).build();

        return loginDto;
    }
}
