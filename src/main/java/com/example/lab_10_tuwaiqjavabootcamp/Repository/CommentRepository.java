package com.example.lab_10_tuwaiqjavabootcamp.Repository;

import com.example.lab_10_tuwaiqjavabootcamp.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    Comment findCommentById(Integer comment_id);

}
