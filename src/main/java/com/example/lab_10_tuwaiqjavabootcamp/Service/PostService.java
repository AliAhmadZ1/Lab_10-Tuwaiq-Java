package com.example.lab_10_tuwaiqjavabootcamp.Service;

import com.example.lab_10_tuwaiqjavabootcamp.Model.Category;
import com.example.lab_10_tuwaiqjavabootcamp.Model.Post;
import com.example.lab_10_tuwaiqjavabootcamp.Model.User;
import com.example.lab_10_tuwaiqjavabootcamp.Repository.CategoryRepository;
import com.example.lab_10_tuwaiqjavabootcamp.Repository.PostRepository;
import com.example.lab_10_tuwaiqjavabootcamp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Boolean addPost(Post post){
        Category category = categoryRepository.findCategoryById(post.getCategory_id());
        User user = userRepository.findUserById(post.getUser_id());
        if (category==null|| user ==null)
            return false;
        post.setPublish_date(LocalDate.now());
        postRepository.save(post);
        return true;
    }

    public Boolean updatePost(Integer id,Post post){
        Post oldPost = postRepository.findPostById(id);
        Category category = categoryRepository.findCategoryById(post.getCategory_id());
        User user = userRepository.findUserById(post.getUser_id());
        if (category==null|| user ==null||oldPost ==null)
            return false;

        oldPost.setContent(post.getContent());
        oldPost.setTitle(post.getTitle());
        postRepository.save(oldPost);
        return true;
    }

    public Boolean deletePost(Integer id){
        Post post = postRepository.findPostById(id);
        if (post==null)
            return false;
        postRepository.delete(post);
        return true;
    }

    // 1
    public List<Post> getPostsByUserId(Integer user_id){
        return postRepository.getPostByUser_id(user_id);
    }

    // 2
    public Post getByTitle(String title){
        return postRepository.findPostByTitle(title);
    }

    // 4
    public List<Post> getBeforeDate(LocalDate date){
        return postRepository.getPostByPublish_dateBefore(date);
    }

}
