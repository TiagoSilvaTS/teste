package com.testing.demo.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.testing.demo.Properties.JwtProperties;
import com.testing.demo.dto.PrincipalDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtServiceImpl implements JwtService {

    private final JwtProperties jwtProperties;

    @Override
    public String createToken(PrincipalDto principalDto) {
        return Jwts.builder()
                .setSubject(principalDto.getId().toString())
                .claim("name", principalDto.getName())
                .claim("email",principalDto.getEmail())
                .claim("userType", principalDto.getRole())
                .setIssuer("App")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtProperties.getExpiration()))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .compact();
    }

    @Override
    public Long validateTokenAndGetId(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(jwtProperties.getSecret())
                .requireIssuer("App")
                .parseClaimsJws(token);


        return Long.parseLong(claims.getBody().getSubject());
    }
}