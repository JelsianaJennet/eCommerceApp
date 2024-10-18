package com.example.productcatalog.services;

import com.example.productcatalog.models.Category;
import com.example.productcatalog.models.Price;
import com.example.productcatalog.models.Product;

import java.util.List;
import java.util.UUID;

public interface ProductServicedb {

    Product getProductByID(UUID uuid);

    List<Product> getAllProducts();

    List<Product> getAllProductsByCategory(List<String> categories);

    Product createProduct(Product product, String categoryName, Price price);

    Product updateProductTitle(String title, UUID id);

    void deleteProductByID(String uuid);

}
