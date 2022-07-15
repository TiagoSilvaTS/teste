package com.testing.demo.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.testing.demo.exception.AppError;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserAuthenticationEntryPoint
        implements AuthenticationEntryPoint {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        var errList = new ArrayList<String>();
        errList.add(authException.getMessage());
        MAPPER.writeValue(
                response.getOutputStream(),
                AppError.builder()
                        .httpMethod(request.getMethod())
                        .exception(authException.getClass().getSimpleName())
                        .message(errList)
                        //TODO: figure out why getRequestURI and not getServletPath
                        .path(request.getRequestURI())
                        .build()
        );
    }
}
