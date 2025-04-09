package com.example.lab_10_tuwaiqjavabootcamp.Repository;

import com.example.lab_10_tuwaiqjavabootcamp.Model.Category;
import com.example.lab_10_tuwaiqjavabootcamp.Model.Post;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Category findCategoryById(Integer category_id);

    Category findCategoryByName(String name);

    @Query("select p from Post p where p.user_id=?1 ")
    List<Post> getAllPostsByUserAndCategory(Integer user_id);

}
