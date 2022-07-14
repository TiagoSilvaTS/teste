package com.testing.demo.dto;

import com.testing.demo.enums.UserType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    @Size(min=5,max=16,message = "Name must be bigger than 5 and smaller than 16")
    @NotNull
    String name;


   @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message= "must have at least 8 chars and one number")
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType role;
}
