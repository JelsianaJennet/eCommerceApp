package com.example.productcatalog.services;

import com.example.productcatalog.Repositories.CategoryRepository;
import com.example.productcatalog.Repositories.PriceRepository;
import com.example.productcatalog.Repositories.ProductRepository;
import com.example.productcatalog.models.Category;
import com.example.productcatalog.models.Price;
import com.example.productcatalog.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("ProductServiceDbImpl")
public class ProductServiceImpl implements ProductServicedb{

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    private final PriceRepository priceRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
                              PriceRepository priceRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
    }

    @Override
    public Product getProductByID(UUID uuid) {
        return productRepository.getProductsById(uuid);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public List<Product> getAllProductsByCategory(List<String> categories) {

        List<Category> categoryList = categoryRepository.findAllByNameIn(categories);
        List<Product> productList = productRepository.getAllByCategoryIn(categoryList);

        return productList;
    }

    @Override
    public Product createProduct(Product product, String categoryName, Price price) {

        Optional<Category> category = categoryRepository.getCategoryByName(categoryName);

        Category category1 = new Category();

        if(category.isEmpty()) {
            // Create new Category
            category1.setName(categoryName);
            category1 = categoryRepository.save(category1);
        }
        else {
            category1 = category.get();
        }

        product.setCategory(category1);

        Price price1 = priceRepository.save(price);
        product.setPrice(price1);

        return productRepository.save(product);
    }

    @Override
    public Product updateProductTitle(String title, UUID id) {
        Product product = productRepository.getProductsById(id);
        product.setTitle(title);

        return productRepository.save(product);
    }

    @Override
    public void deleteProductByID(String uuid) {
        Product product = productRepository.getProductsById(UUID.fromString(uuid));
        productRepository.delete(product);

    }
}
