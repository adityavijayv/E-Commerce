package com.example.Product.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
    private Integer id;
    private String categoryName;
    private String description;

    private List<ProductDTO> productDTOList;
}
