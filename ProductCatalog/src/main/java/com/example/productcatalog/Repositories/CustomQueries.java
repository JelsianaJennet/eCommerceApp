package com.example.productcatalog.Repositories;

public interface CustomQueries {

    String FIND_BY_ID = "select * from product where id = :id";

    String FIND_ALL_BY_TITLE = "select * from product join product_orders " +
            "on product.id = product_orders.product_id where title = :title";

    String FIND_ALL_PRODUCTS = "select * from product";

    String FIND_ALL_CATEGORIES = "select * from category";

}
