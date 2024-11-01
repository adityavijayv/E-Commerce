package com.example.Product.Service;

import com.example.Product.DTO.CategoryDTO;
import com.example.Product.DTO.ProductDTO;
import com.example.Product.Model.Category;
import com.example.Product.Model.Product;
import com.example.Product.Repository.CategoryRepository;
import com.example.Product.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<ProductDTO> getAllProduct(){
        List <ProductDTO> productDTOList = new ArrayList<>();
        productRepository.findAll().forEach(product -> {
            ProductDTO productDTO = new ProductDTO();

            productDTO.setId(product.getId());
            productDTO.setProductName(product.getProductName());
            productDTO.setUnitPrice(product.getUnitPrice());
            productDTO.setUnitInStock(product.getUnitInStock());
            productDTO.setUnitsOnOrder(product.getUnitsOnOrder());
            productDTO.setCategoryId(product.getCategory().getId());
            productDTO.setCategoryName(product.getCategory().getCategoryName());

            productDTOList.add(productDTO);
        });
        return productDTOList;
    }

    public int saveProduct(ProductDTO productDTO , Integer categoryId){
        ProductDTO DTO = new ProductDTO();
        Product product = new Product();
        Optional <Category> category = categoryRepository.findById(categoryId);
        if(category.isPresent()){

            product.setCategory(category.get());
            product.setProductName(productDTO.getProductName());
            product.setId(productDTO.getId());
            product.setUnitInStock(productDTO.getUnitInStock());
            product.setUnitPrice(productDTO.getUnitPrice());
            product.setUnitsOnOrder(productDTO.getUnitsOnOrder());

            Product save = productRepository.save(product);
            productDTO.setId(save.getId());

        }else {
            throw new RuntimeException("Given Category ID is not Present");
        }
        return productDTO.getId();
    }

    public ProductDTO getProductById(Integer Id){
        Optional <Product> product = productRepository.findById(Id);
        ProductDTO productDTO = new ProductDTO();
       if(!product.isPresent()){
           throw new RuntimeException("Given Id not Present");
       }
            productDTO.setId(product.get().getId());
            productDTO.setProductName(product.get().getProductName());
            productDTO.setUnitPrice(product.get().getUnitPrice());
            productDTO.setUnitInStock(product.get().getUnitInStock());
            productDTO.setUnitsOnOrder(product.get().getUnitsOnOrder());

        return productDTO;
    }

    public ProductDTO updateProduct(ProductDTO productDTO){
        Optional <Product> optionalProduct = productRepository.findById(productDTO.getId());
        ProductDTO dto = new ProductDTO();
        Product product = new Product();
        if(optionalProduct.isPresent()){

            product.setProductName(productDTO.getProductName());
            product.setId(productDTO.getId());
            product.setUnitInStock(productDTO.getUnitInStock());
            product.setUnitPrice(productDTO.getUnitPrice());
            product.setUnitsOnOrder(productDTO.getUnitsOnOrder());

            Product save = productRepository.save(product);
            dto.setId(save.getId());

        }else {
            throw new RuntimeException("Given ID is not Present");
        }
        return dto;

    }

    public void deleteProduct (Integer id){
        Optional <Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Given ID is not present");
        }
    }

    public List<ProductDTO> getAllProductsByCategory(Integer categoryId){
        Optional <Category> category = categoryRepository.findById(categoryId);
        List <ProductDTO> productDTOList = new ArrayList<>();
        CategoryDTO categoryDTO = new CategoryDTO();

        if(!category.isPresent()){
            throw new RuntimeException("Given Id not Present");
        }
        else {
            for (Product product : category.get().getProductList()) {
                ProductDTO productDTO = new ProductDTO();

                productDTO.setProductName(product.getProductName());
                productDTO.setId(product.getId());
                productDTO.setUnitPrice(product.getUnitPrice());
                productDTO.setUnitInStock(product.getUnitInStock());
                productDTO.setUnitsOnOrder(product.getUnitsOnOrder());
                productDTO.setCategoryId(product.getCategory().getId());
                productDTO.setCategoryName(product.getCategory().getCategoryName());

                productDTOList.add(productDTO);

            }
        }
        return productDTOList;
    }
}