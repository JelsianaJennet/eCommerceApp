package com.example.productcatalog.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryListDto {
    List<String> categories;
}
