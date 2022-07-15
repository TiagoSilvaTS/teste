package com.testing.demo.util;

import com.testing.demo.dto.PrincipalDto;

public interface JwtService {
    String createToken(PrincipalDto principalDto);

    Long validateTokenAndGetId(String token);
}
