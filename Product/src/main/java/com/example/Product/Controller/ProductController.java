package com.example.Product.Controller;

import com.example.Product.DTO.CategoryDTO;
import com.example.Product.DTO.ProductDTO;
import com.example.Product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("getProduct/all")
    private List<ProductDTO> getAllProduct(){
        return productService.getAllProduct();
    }

    @PostMapping("/saveProduct")
    private int saveProduct (@RequestBody ProductDTO productDTO){
        int saveProduct = productService.saveProduct(productDTO ,productDTO.getCategoryId() );
        return saveProduct;

    }

    @DeleteMapping("/deleteProduct/by/id/{id}")
    private void deleteProduct(@PathVariable("id") int id)
    {
        productService.deleteProduct(id);
    }

    @GetMapping("/getProduct/by/id/{id}")
    private ProductDTO getProductById(@PathVariable("id") int id) {
        return productService.getProductById(id);
    }

    @PutMapping("/updateProduct")
    private int updateProduct( @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(productDTO).getId();
    }

    @GetMapping("getProductsByCategory/by/id/{id}")
    private List<ProductDTO> getProductsByCategory(@PathVariable ("id") Integer categoryId){
        return productService.getAllProductsByCategory(categoryId);
    }


}
