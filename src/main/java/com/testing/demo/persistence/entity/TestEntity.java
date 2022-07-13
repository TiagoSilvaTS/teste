package com.testing.demo.persistence.entity;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

public class TestEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
  private  long id;

    @Column(nullable = false)
  private  String name;
}
