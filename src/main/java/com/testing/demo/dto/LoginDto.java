package com.testing.demo.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {

    @NotNull(message="Email should not be empty")
    private String email;

   @NotNull(message="Password must not be empty")
    private String password;

}
