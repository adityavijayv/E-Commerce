package com.example.Product.Service;

import com.example.Product.DTO.CategoryDTO;
import com.example.Product.DTO.ProductDTO;
import com.example.Product.Model.Category;
import com.example.Product.Repository.CategoryRepository;
import com.example.Product.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategory(){
        List <CategoryDTO> categoryDTOList = new ArrayList<>();
        List<Category> categoryList=categoryRepository.findAll();

        for (Category category:categoryList) {
            CategoryDTO categoryDTO = new CategoryDTO();

            categoryDTO.setId(category.getId());
            categoryDTO.setCategoryName(category.getCategoryName());
            categoryDTO.setDescription(category.getDescription());

            categoryDTOList.add(categoryDTO);
        }



        return categoryDTOList;
    }

    public int saveCategory(CategoryDTO categoryDTO){
        Category category = new Category();


        category.setId(categoryDTO.getId());
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setDescription(categoryDTO.getDescription());

        Category save = categoryRepository.save(category);
        categoryDTO.setId(save.getId());


        return categoryDTO.getId();
    }


    public CategoryDTO getCategoryById(Integer Id){
        Optional <Category> category = categoryRepository.findById(Id);
        CategoryDTO categoryDTO = new CategoryDTO();
        if(!category.isPresent()){
            throw new RuntimeException("Given ID is not present");
        }

        categoryDTO.setId(category.get().getId());
        categoryDTO.setCategoryName(category.get().getCategoryName());
        categoryDTO.setDescription(category.get().getDescription());

        return categoryDTO;
    }


    public CategoryDTO updateCategory(CategoryDTO categoryDTO){
        Optional <Category> optionalCategory = categoryRepository.findById(categoryDTO.getId());
        Category category = new Category();
        CategoryDTO dto = new CategoryDTO();

        if(optionalCategory.isPresent()){
            category.setId(categoryDTO.getId());
            category.setCategoryName(categoryDTO.getCategoryName());
            category.setDescription(categoryDTO.getDescription());

            Category update = categoryRepository.save(category);
            dto.setId(update.getId());

        }else {
            throw new RuntimeException("Given ID is not Present");
        }
        return dto;
    }


    public void deleteCategory(Integer Id){
        Optional <Category> category = categoryRepository.findById(Id);

        if(category.isPresent()){
            categoryRepository.deleteById(Id);
        }
        else {
            throw new RuntimeException("Given ID is not Present");
        }
    }




}
