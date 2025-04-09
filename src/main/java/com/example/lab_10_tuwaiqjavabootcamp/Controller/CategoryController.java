package com.example.lab_10_tuwaiqjavabootcamp.Controller;

import com.example.lab_10_tuwaiqjavabootcamp.ApiResponse.ApiResponse;
import com.example.lab_10_tuwaiqjavabootcamp.Model.Category;
import com.example.lab_10_tuwaiqjavabootcamp.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getAllCategories(){
        if (categoryService.getAllCategories().isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("there no category"));
        return ResponseEntity.status(200).body(categoryService.getAllCategories());
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody@Valid Category category, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        boolean isAdded = categoryService.addCategory(category);
        if (isAdded)
            return ResponseEntity.status(200).body(new ApiResponse("new category is added"));
        return ResponseEntity.status(400).body(new ApiResponse("this category is already exist"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @RequestBody@Valid Category category, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        boolean isUpdated = categoryService.updateCategory(id, category);
        if (isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("category is updated"));
        return ResponseEntity.status(400).body(new ApiResponse("not found or already exist"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id){
        boolean isDeleted = categoryService.deleteCategory(id);
        if (isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("category is deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

}
