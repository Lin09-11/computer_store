package com.example.mycomputerstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Integer cid;//购物车id
    private Integer uid;//用户id
    private Integer pid;//商品id
    private Long price;//总价
    private Integer num;//数量
}
