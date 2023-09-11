package com.example.mycomputerstore.mapper;

import com.example.mycomputerstore.entity.Product;

import java.util.List;

public interface ProductMapper {

    /**
     * 展示销售前4的商品
     * @return
     */
    List<Product> findHotList();

    /**
     * 显示商品详情信息
     * @param id
     * @return
     */
    Product findById(Integer id);
}
