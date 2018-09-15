package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

  private static final long serialVersionUID = -2315659388348422700L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name", nullable = false, length = 128)
  private String name;
  @Column(name = "password", nullable = false, length = 255)
  private String password;
  @Column(name = "email", nullable = false, unique = true, length = 255)
  private String email;
  @Column(name = "admin_flag", nullable = false)
  private Boolean admin;

  public static User of(String name, String password, String email) {
    return User.builder().name(name).password(password).email(email).admin(false).build();
  }

}
