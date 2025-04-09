package com.example.lab_10_tuwaiqjavabootcamp.Controller;

import com.example.lab_10_tuwaiqjavabootcamp.ApiResponse.ApiResponse;
import com.example.lab_10_tuwaiqjavabootcamp.Model.Post;
import com.example.lab_10_tuwaiqjavabootcamp.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/get")
    public ResponseEntity getAllPosts() {
        if (postService.getAllPosts().isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("there no posts"));
        return ResponseEntity.status(200).body(postService.getAllPosts());
    }

    @PostMapping("/add")
    public ResponseEntity addPost(@RequestBody @Valid Post post, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        boolean isAdded = postService.addPost(post);
        if (isAdded)
            return ResponseEntity.status(200).body(new ApiResponse("new post is added"));
        return ResponseEntity.status(400).body(new ApiResponse("user or category not found"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@PathVariable Integer id, @RequestBody @Valid Post post, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        boolean isUpdated = postService.updatePost(id, post);
        if (isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("post is updated"));
        return ResponseEntity.status(400).body(new ApiResponse("post,user or category not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id) {
        boolean isDeleted = postService.deletePost(id);
        if (isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("post is deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    // endpoint 1
    @GetMapping("/get-by-user/{id}")
    public ResponseEntity getByUser(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(postService.getPostsByUserId(id));
    }

    // endpoint 2
    @GetMapping("/get-by-title/{title}")
    public ResponseEntity getByTitle(@PathVariable String title) {
        if (postService.getByTitle(title) == null)
            return ResponseEntity.status(400).body(new ApiResponse("There are no post with " + title + " title"));
        return ResponseEntity.status(200).body(postService.getByTitle(title));
    }

    //endpoint 4
    @GetMapping("/get-before-date/{date}")
    public ResponseEntity getBeforeDate(@PathVariable LocalDate date) {
        return ResponseEntity.status(200).body(postService.getBeforeDate(date));
    }

    // endpoint 7
    @PutMapping("/edit-title/{user_id},{post_id},{title}")
    public ResponseEntity editTitle(@PathVariable Integer user_id, @PathVariable Integer post_id, String title) {
        boolean isEdited = postService.editTitle(user_id, post_id, title);
        if (isEdited)
            return ResponseEntity.status(200).body(new ApiResponse("Title is updated"));
        return ResponseEntity.status(400).body(new ApiResponse("user or post not found or not related"));
    }

}
