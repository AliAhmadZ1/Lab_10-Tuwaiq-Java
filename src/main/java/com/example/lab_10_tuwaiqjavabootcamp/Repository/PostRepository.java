package com.example.lab_10_tuwaiqjavabootcamp.Repository;

import com.example.lab_10_tuwaiqjavabootcamp.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    Post findPostById(Integer id);

}
