package com.example.productcatalog.services;

import com.example.productcatalog.Repositories.CategoryRepository;
import com.example.productcatalog.models.Category;
import com.example.productcatalog.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryServicedb{

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.getallCategories();
    }

}
