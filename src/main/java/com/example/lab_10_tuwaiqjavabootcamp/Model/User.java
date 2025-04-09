package com.example.lab_10_tuwaiqjavabootcamp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {

    //• user_id
    //• username
    //• password
    //• email
    //• registration_date
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "username cannot be empty")
    @Size(min = 4,max = 20, message = "should be 4 characters to 20 characters")
    @Column(columnDefinition = "varchar(20) not null unique")
    @Check(constraints = "length(username)>=4")
    private String username;
    @NotEmpty(message = "password cannot be empty")
    @Size(min = 4,max = 16, message = "should be 4 characters to 16 characters")
    @Column(columnDefinition = "varchar(16) not null")
    @Check(constraints = "length(username)>=4")
    private String password;
    @NotEmpty(message = "email cannot be empty")
    @Email(message = "should be in format (**@**.**)")
    @Column(columnDefinition = "varchar(20) not null unique")
    @Check(constraints = "email like '%__@__%.__%'")
    private String email;
    private LocalDate registration_date;
}
