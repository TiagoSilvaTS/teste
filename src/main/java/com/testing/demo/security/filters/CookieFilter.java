package com.testing.demo.security.filters;

import com.testing.demo.security.UserAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class CookieFilter extends OncePerRequestFilter {
    private final String COOKIE = "cookie_auth";
    private final UserAuthenticationProvider authenticationProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        Optional<Cookie> authCookie = Stream.of(Optional.ofNullable(request.getCookies())
                        .orElse(new Cookie[0]))
                .filter(cookie -> COOKIE.equals(cookie.getName()) &&
                        Objects.nonNull(cookie.getValue()) &&
                        !cookie.getValue().isEmpty())
                .findFirst();

        try {
            authCookie.ifPresent(cookie -> {
                final var authentication = authenticationProvider.validateToken(cookie.getValue());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            });

        } catch (RuntimeException e) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}
