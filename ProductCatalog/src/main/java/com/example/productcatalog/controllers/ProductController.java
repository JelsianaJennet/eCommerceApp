package com.example.productcatalog.controllers;

import com.example.productcatalog.dtos.CategoryListDto;
import com.example.productcatalog.dtos.RequestProductDto;
import com.example.productcatalog.dtos.ResponseProductDto;
import com.example.productcatalog.dtos.UpdateProductDto;
import com.example.productcatalog.models.Category;
import com.example.productcatalog.models.Price;
import com.example.productcatalog.models.Product;
import com.example.productcatalog.services.ProductService;
import com.example.productcatalog.services.ProductServicedb;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductServicedb productServicedb;

    public ProductController(ProductServicedb productServicedb) {
        this.productServicedb = productServicedb;
    }

    @GetMapping("/{id}")
    public ResponseProductDto getProductById(@PathVariable("id") String uuid) {

        System.out.println("hai product controller");
        return null;

//        Product product = productServicedb.getProductByID(UUID.fromString(uuid));
//
//        return convertProductToResponseProductDto(product);
    }

    @PostMapping
    public ResponseProductDto createProduct(@RequestBody RequestProductDto requestProductDto) {

        Product product = new Product();
        product.setTitle((requestProductDto.getTitle()));
        product.setDescription(requestProductDto.getDescription());
        product.setImage(requestProductDto.getImage());

        Price price = new Price(requestProductDto.getCurrency(), requestProductDto.getPrice());

        Product resProduct = productServicedb.createProduct(product, requestProductDto.getCategoryName(), price);

        return convertProductToResponseProductDto(resProduct);
    }

    @GetMapping("/all")
    public List<ResponseProductDto> getAllProducts() {

        List<Product> products = productServicedb.getAllProducts();
        List<ResponseProductDto> responseProductDtos = new ArrayList<>();

        products.forEach((product -> {
            responseProductDtos.add(convertProductToResponseProductDto(product));
        }));

        return  responseProductDtos;
    }

    private ResponseProductDto convertProductToResponseProductDto(Product product) {

        ResponseProductDto productDto = new ResponseProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setImage(product.getImage());
        productDto.setPrice(product.getPrice());

        return productDto;
    }

    @PutMapping
    public ResponseProductDto updateProductTitle(@RequestBody UpdateProductDto updateProductDto) {

       Product resProduct = productServicedb.updateProductTitle(updateProductDto.getTitle(), UUID.fromString(updateProductDto.getId()));

        return convertProductToResponseProductDto(resProduct);
    }

    @GetMapping("/byCategory")
    public List<ResponseProductDto> getAllProductsInCategory(@RequestBody CategoryListDto categories) {

        List<Product> products = productServicedb.getAllProductsByCategory(categories.getCategories());
        List<ResponseProductDto> responseProductDtos = new ArrayList<>();

        products.forEach(product -> {
            responseProductDtos.add(convertProductToResponseProductDto(product));
        });

        return responseProductDtos;
    }

    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable("id") String uuid){
        productServicedb.deleteProductByID(uuid);
        return "Product Deleted successfully";
    }


}
