package com.example.productcatalog.controllers;

import com.example.productcatalog.dtos.ExceptionData;
import com.example.productcatalog.dtos.GenericProductDto;
import com.example.productcatalog.exceptions.NotFoundException;
import com.example.productcatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fakeStore/products")
public class ProductFakeStoreController {

    private ProductService productService;

    // Constructor Injection
    // public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
    // @Primary annotation to specify default service

    public ProductFakeStoreController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }
    // setter injection and field injection by Autowired is not recommended

    @GetMapping
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    //localhost:8080/products/123
    @GetMapping("/{id}")
    public GenericProductDto getProductsById(@PathVariable("id") Long id) throws NotFoundException{
//        return "Product id: " + id;
        return productService.getProductById(id);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id) throws NotFoundException{
        ResponseEntity<GenericProductDto> response = new ResponseEntity<>(productService.deleteProduct(id), HttpStatusCode.valueOf(201));
        return response;
//  productService.deleteProduct(id);

    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto) {
//        return "New product created "+ UUID.randomUUID();
        return productService.createProduct(genericProductDto);
    }

    @PutMapping("/{id}")
    public GenericProductDto updateProductById(@RequestBody GenericProductDto genericProductDto) {
        return productService.updateProduct(genericProductDto, genericProductDto.getId());
    }

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionData> handleNotFoundException(NotFoundException notFoundException) {
        return new ResponseEntity<>(new ExceptionData(HttpStatus.NOT_FOUND,notFoundException.getMessage()), HttpStatus.NOT_FOUND);

    }
}
