package com.example.productcatalog.dtos;

import com.example.productcatalog.models.Category;
import com.example.productcatalog.models.Price;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestProductDto {
    private String title; //name
    private String description;
    private String image;
    private String categoryName;
    private String currency;
    private double price;
}
