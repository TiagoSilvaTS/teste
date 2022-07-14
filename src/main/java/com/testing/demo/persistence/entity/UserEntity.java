package com.testing.demo.persistence.entity;


import com.testing.demo.enums.UserType;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "test")

public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
  private  long id;

    @Column(name="Name", nullable = false)
  private  String name;

    @Column(name= "email",nullable= false)
    private String email;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType role;
}
