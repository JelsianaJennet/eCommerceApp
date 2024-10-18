package com.example.productcatalog.dtos;

import com.example.productcatalog.models.Price;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseProductDto {
    private String title;
    private String description;
    private String image;
    private Price price;
}
