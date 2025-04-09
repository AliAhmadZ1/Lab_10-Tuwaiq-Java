package com.example.lab_10_tuwaiqjavabootcamp.Repository;

import com.example.lab_10_tuwaiqjavabootcamp.Model.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Category findCategoryById(Integer category_id);

    Category findCategoryByName(String name);

}
