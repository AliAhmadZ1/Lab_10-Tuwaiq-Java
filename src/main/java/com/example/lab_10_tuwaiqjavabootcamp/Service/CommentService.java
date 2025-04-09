package com.example.lab_10_tuwaiqjavabootcamp.Service;

import com.example.lab_10_tuwaiqjavabootcamp.Model.Comment;
import com.example.lab_10_tuwaiqjavabootcamp.Model.Post;
import com.example.lab_10_tuwaiqjavabootcamp.Model.User;
import com.example.lab_10_tuwaiqjavabootcamp.Repository.CommentRepository;
import com.example.lab_10_tuwaiqjavabootcamp.Repository.PostRepository;
import com.example.lab_10_tuwaiqjavabootcamp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public Boolean addComment(Comment comment){
        User user = userRepository.findUserById(comment.getUser_id());
        Post post = postRepository.findPostById(comment.getPost_id());
        if (user==null||post==null)
            return false;
        comment.setComment_date(LocalDate.now());
        commentRepository.save(comment);
        return true;
    }

    public Boolean updateComment(Integer id,Comment comment){
        Comment oldComment = commentRepository.findCommentById(id);
        User user = userRepository.findUserById(comment.getUser_id());
        Post post = postRepository.findPostById(comment.getPost_id());
        if (user==null||post==null||oldComment==null)
            return false;
        oldComment.setContent(comment.getContent());
        commentRepository.save(oldComment);
        return true;
    }

    public Boolean deleteComment(Integer id){
        Comment comment = commentRepository.findCommentById(id);
        if (comment==null)
            return false;
        commentRepository.delete(comment);
        return true;
    }

    public List<Comment> getAllComments(Integer post_id){
        return commentRepository.getAllComments(post_id);
    }
}
