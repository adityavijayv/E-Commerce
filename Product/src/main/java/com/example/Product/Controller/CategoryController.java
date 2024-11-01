package com.example.Product.Controller;

import com.example.Product.DTO.CategoryDTO;
import com.example.Product.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("getCategory/all")
    private List<CategoryDTO> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @PostMapping("/saveCategory")
    private int saveCategory (@RequestBody CategoryDTO categoryDTO){
        int saveCategory = categoryService.saveCategory(categoryDTO);
        return saveCategory;

    }

    @DeleteMapping("/deleteCategory/by/id/{id}")
    private void deleteCategory(@PathVariable("id") int id)
    {
       categoryService.deleteCategory(id);
    }

    @GetMapping("/getCategory/by/id/{id}")
    private CategoryDTO getCategoryById(@PathVariable("id") int id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/updateCategory/{id}")
    private int updateCategory(@PathVariable("id") int id, @RequestBody CategoryDTO categoryDTO) {
        categoryDTO.setId(id);
        return (categoryService.updateCategory(categoryDTO).getId());
    }

}
