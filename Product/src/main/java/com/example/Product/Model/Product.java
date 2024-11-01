package com.example.Product.Model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private  String productName;
    private  Integer unitPrice;
    private Integer unitInStock;
    private Integer unitsOnOrder;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Category_id")
    private Category category;

}
