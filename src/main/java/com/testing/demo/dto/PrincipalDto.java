package com.testing.demo.dto;

import com.testing.demo.enums.UserType;
import lombok.*;

import javax.validation.constraints.NotNull;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrincipalDto {

   private Long id;
   private String name;
    private String email;
 private UserType role;

}
