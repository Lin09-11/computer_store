package com.example.mycomputerstore.service;

import com.example.mycomputerstore.entity.Cart;

public interface ICartService {


    /**
     * 将商品添加到购物车
     * @param uid 当前登录用户的id
     * @param pid 商品的id
     * @param amount 增加的数量
     * @param username 当前登录的用户名
     */
    void addToCart(Integer uid, Integer pid, Integer amount, String username);
}
