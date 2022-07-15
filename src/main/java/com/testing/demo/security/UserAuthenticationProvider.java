package com.testing.demo.security;


import com.testing.demo.dto.PrincipalDto;
import com.testing.demo.exception.NotFound.UserNotFoundException;
import com.testing.demo.persistence.entity.UserEntity;
import com.testing.demo.persistence.repository.TestRepo;
import com.testing.demo.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class UserAuthenticationProvider {
    private static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    private final TestRepo userRepository;
    private final JwtService jwtService;


    public Authentication validateToken(String token) {
        Long userId = jwtService.validateTokenAndGetId(token);

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    return new UserNotFoundException(String.format("User not found with id %s", userId));
                });

        PrincipalDto principalDto = PrincipalDto.builder()
                .role(user.getRole())
                .name(user.getName())
                .id(user.getId())
                .build();

        return new UsernamePasswordAuthenticationToken(
                principalDto,
                null,
                Collections.singletonList(new SimpleGrantedAuthority(principalDto.getRole().name())));
    }


}
