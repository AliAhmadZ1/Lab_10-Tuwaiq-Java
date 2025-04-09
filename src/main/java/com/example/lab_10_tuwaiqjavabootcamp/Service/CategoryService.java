package com.example.lab_10_tuwaiqjavabootcamp.Service;

import com.example.lab_10_tuwaiqjavabootcamp.ApiResponse.ApiResponse;
import com.example.lab_10_tuwaiqjavabootcamp.Model.Category;
import com.example.lab_10_tuwaiqjavabootcamp.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Boolean addCategory(Category category){
        Category newCategory = categoryRepository.findCategoryByName(category.getName());
        if (newCategory==null){
            categoryRepository.save(category);
            return true;
        }
        return false;
    }

    public Boolean updateCategory(Integer id,Category category){
        Category oldCategory = categoryRepository.findCategoryById(id);
        Category checkName = categoryRepository.findCategoryByName(category.getName());
        if (oldCategory==null||checkName!=null)
            return false;

        oldCategory.setName(category.getName());
        categoryRepository.save(oldCategory);
        return true;
    }

    public Boolean deleteCategory(Integer id){
        Category category = categoryRepository.findCategoryById(id);
        if (category==null)
            return false;
        categoryRepository.delete(category);
        return true;
    }

}
