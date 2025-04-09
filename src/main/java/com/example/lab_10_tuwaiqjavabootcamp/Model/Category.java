package com.example.lab_10_tuwaiqjavabootcamp.Model;

import jakarta.annotation.security.DenyAll;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Category {
    //• category_id
    //• name

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "category name cannot be empty")
    @Size(min = 4,max = 20, message = "should be 4 characters to 20 characters")
    @Column(columnDefinition = "varchar(20) not null")
    @Check(constraints = "length(name)>=4")
    private String name;
}
