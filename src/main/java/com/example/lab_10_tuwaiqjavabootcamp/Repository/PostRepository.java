package com.example.lab_10_tuwaiqjavabootcamp.Repository;

import com.example.lab_10_tuwaiqjavabootcamp.Model.Post;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    Post findPostById(Integer id);

    @Query("select p from Post p where p.user_id=?1")
    List<Post> getPostByUser_id(Integer user_id);

    Post findPostByTitle(String title);

    @Query("select p from Post p where p.publish_date>=?1")
    List<Post> getPostByPublish_dateBefore(LocalDate date);

}
