package com.example.mycomputerstore.service;

import com.example.mycomputerstore.entity.Product;

import java.util.List;

public interface IProductService {

    /**
     * 查询热销商品的前四
     * @return
     */
    List<Product> findHotList();
}
