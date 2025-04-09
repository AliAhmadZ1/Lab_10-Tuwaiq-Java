package com.example.lab_10_tuwaiqjavabootcamp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class Comment {

    //• comment_id
    //• user_id
    //• post_id
    //• content
    //• comment_date

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "user id cannot be null")
    private Integer user_id;
    @NotNull(message = "post id cannot be null")
    private Integer post_id;
    @NotEmpty(message = "content cannot be empty")
    @Size(max = 300, message = "exceeds 300 characters")
    @Column(columnDefinition = "varchar(300) not null")
    @Check(constraints = "length(content)<=300")
    private String content;
    private LocalDate comment_date;

}
