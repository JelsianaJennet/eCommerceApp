package com.example.productcatalog.Repositories;

import com.example.productcatalog.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
