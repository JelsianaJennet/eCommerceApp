package com.example.productcatalog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends  BaseModel{

    private String title; //name
    private String description;
    private String image;
    // P : C ->
    // 1:1
    // M:1
    // M:1
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name ="category")
    private Category category;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    // to persist the data on creating the record for reference variable
    private Price price = new Price();

}
