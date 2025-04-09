package com.example.lab_10_tuwaiqjavabootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab10TuwaiqJavaBootCampApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab10TuwaiqJavaBootCampApplication.class, args);
    }

    //1. User
        //• user_id
        //• username
        //• password
        //• email
        //• registration_date
    //2. Category
        //• category_id
        //• name
    //3. Post
        //• post_id
        //• category_id
        //• title
        //• content
        //• user_id
        //• publish_date
    //4. Comment
        //• comment_id
        //• user_id
        //• post_id
        //• content
        //• comment_date

    //Endpoints
    //- CRUD for All Model
    //- 8 Endpoints use JPA and JPQL ( ex. get all post by user_id , get post by title, get
    //all comment for one post by post_id , get all post before date by date )
}
