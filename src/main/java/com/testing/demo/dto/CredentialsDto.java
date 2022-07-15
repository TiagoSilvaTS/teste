package com.testing.demo.dto;


import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CredentialsDto {
    @NotNull(message="Email should not be empty")
    private String email;

    @NotNull(message="Password must not be empty")
    private String password;
}
