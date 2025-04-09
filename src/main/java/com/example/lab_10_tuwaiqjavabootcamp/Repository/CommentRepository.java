package com.example.lab_10_tuwaiqjavabootcamp.Repository;

import com.example.lab_10_tuwaiqjavabootcamp.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    Comment findCommentById(Integer comment_id);

    @Query("select c from Comment c where c.post_id=?1")
    List<Comment> getAllComments(Integer post_id);

}
