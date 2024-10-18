package com.example.productcatalog.Repositories;

import com.example.productcatalog.models.Category;
import com.example.productcatalog.models.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    Product findByTitleEqualsAndPrice_Price(String title, double price);

    Product findByTitleEquals(String title);

    List<Product> findAllByPrice_Currency(String currency);


    List<Product> getAllByTitleLike(String title);

    @Query(value = CustomQueries.FIND_BY_ID, nativeQuery = true)
    Product getProductsById(UUID id);


    List<Product> getAllByCategoryIn(List<Category> categories);

    @Query(value = CustomQueries.FIND_ALL_PRODUCTS, nativeQuery = true)
    List<Product> getAllProducts();

//    Product updateProductByCategory(Category category);
}
