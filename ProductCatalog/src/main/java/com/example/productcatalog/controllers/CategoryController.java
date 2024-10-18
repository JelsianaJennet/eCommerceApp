package com.example.productcatalog.controllers;

import com.example.productcatalog.dtos.ResponseProductDto;
import com.example.productcatalog.models.Category;
import com.example.productcatalog.models.Product;
import com.example.productcatalog.services.CategoryServicedb;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryServicedb categoryServicedb;

    public CategoryController(CategoryServicedb categoryServicedb){
        this.categoryServicedb = categoryServicedb;
    }

    @GetMapping
    public List<String> getAllCategories() {

        List<Category> categories = categoryServicedb.getAllCategories();
        List<String> categoryNames = new ArrayList<>();

        categories.forEach(category -> {
            categoryNames.add(category.getName());
        });

        return categoryNames;
    }
}
