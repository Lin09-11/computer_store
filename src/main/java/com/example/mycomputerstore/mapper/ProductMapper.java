package com.example.mycomputerstore.mapper;

import com.example.mycomputerstore.entity.Product;

import java.util.List;

public interface ProductMapper {

    /**
     * 展示销售前4的商品
     * @return
     */
    List<Product> findHotList();
}
