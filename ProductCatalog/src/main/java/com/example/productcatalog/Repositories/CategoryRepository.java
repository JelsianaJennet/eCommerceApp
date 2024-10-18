package com.example.productcatalog.Repositories;

import com.example.productcatalog.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    // GetallCategories
    @Query(value = CustomQueries.FIND_ALL_CATEGORIES, nativeQuery = true)
    List<Category> getallCategories();

    Optional<Category> getCategoryByName(String categoryName);

//    @Query(value = CustomQueries.FIND_ALL_BY_TITLE, nativeQuery = true)
    List<Category> findAllByNameIn(List<String> categoryNames);

    List<Category> findAllByName(List<String> name);
}
