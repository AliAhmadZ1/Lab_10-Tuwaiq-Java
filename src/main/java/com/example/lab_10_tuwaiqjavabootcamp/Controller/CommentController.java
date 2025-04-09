package com.example.lab_10_tuwaiqjavabootcamp.Controller;

import com.example.lab_10_tuwaiqjavabootcamp.ApiResponse.ApiResponse;
import com.example.lab_10_tuwaiqjavabootcamp.Model.Comment;
import com.example.lab_10_tuwaiqjavabootcamp.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/get")
    public ResponseEntity getAllComments(){
        if (commentService.getAllComments().isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("there are no comments"));
        return ResponseEntity.status(200).body(commentService.getAllComments());
    }

    @PostMapping("/add")
    public ResponseEntity addComment(@RequestBody@Valid Comment comment, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        boolean isAdded = commentService.addComment(comment);
        if (isAdded)
            return ResponseEntity.status(200).body(new ApiResponse("new comment is Added"));
        return ResponseEntity.status(400).body(new ApiResponse("user or post not found"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateComment(@PathVariable Integer id,@RequestBody@Valid Comment comment,Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        boolean isUpdated = commentService.updateComment(id, comment);
        if (isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("comment is updated"));
        return ResponseEntity.status(400).body(new ApiResponse("comment, user or post not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id){
        boolean isDeleted = commentService.deleteComment(id);
        if (isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("comment is deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    @GetMapping("/get-comments/{post_id}")
    public ResponseEntity getAllComments(@PathVariable Integer post_id){
        return ResponseEntity.status(200).body(commentService.getAllComments(post_id));
    }
}
