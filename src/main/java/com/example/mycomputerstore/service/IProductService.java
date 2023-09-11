package com.example.mycomputerstore.service;

import com.example.mycomputerstore.entity.Product;

import java.util.List;

public interface IProductService {

    /**
     * 查询热销商品的前四
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
