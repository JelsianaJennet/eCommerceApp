package com.example.productcatalog;

import com.example.productcatalog.controllers.ProductController;
import com.example.productcatalog.dtos.GenericProductDto;
import com.example.productcatalog.dtos.ResponseProductDto;
import com.example.productcatalog.exceptions.NotFoundException;
import com.example.productcatalog.models.Product;
import com.example.productcatalog.services.ProductServiceImpl;
import com.example.productcatalog.services.ProductServicedb;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductCatalogApplicationTests {

    @Autowired
    private ProductServicedb productServicedb;

    @Autowired
    private ProductController productController;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void getProducts() throws Exception {

//        List<Product> products = new ArrayList<>();
//
////        when(productServicedb.getAllProducts()).thenReturn(products);
//
//        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("test", "password"));
//
//        // Act
//        mockMvc.perform(get("/products/all")
//                        .with(authentication(auth))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
////                .andExpect(jsonPath("$.id").value(productId.toString()))
////                .andExpect(jsonPath("$.name").value("Test Product"))
////                .andExpect(jsonPath("$.price").value(100.0));
//
//        // Assert
//        verify(productServicedb, times(1)).getAllProducts();
    }


}
