package com.example.mycomputerstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends BaseEntity{
    private Integer cid;//购物车id
    private Integer uid;//用户id
    private Integer pid;//商品id
    //这里price在数据库中是BIG，所以使用Long
    private Long price;//总价
    private Integer num;//数量
}
