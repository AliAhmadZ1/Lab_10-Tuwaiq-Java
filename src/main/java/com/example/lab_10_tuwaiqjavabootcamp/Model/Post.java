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
public class Post {

    //• post_id
    //• category_id
    //• title
    //• content
    //• user_id
    //• publish_date

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "category id cannot be null")
    private Integer category_id;
    @NotEmpty(message = "title cannot be empty")
    @Size(min = 4,max = 20, message = "title should be 4 characters to 20 characters")
    @Column(columnDefinition = "varchar(20) not null")
    @Check(constraints = "length(title)>=4")
    private String title;
    @NotEmpty(message = "content cannot be empty")
    @Size(min = 50, max = 500, message = "should be 50-500 characters")
    @Column(columnDefinition = "varchar(500) not null")
    @Check(constraints = "length(content)>=50 and length(content)<=500")
    private String content;
    @NotNull(message = "user id cannot be null")
    private Integer user_id;
    private LocalDate publish_date;

}
